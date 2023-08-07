package com.mis.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.common.util.DataPagingDef;
import com.common.util.ResponseCodeMap;
import com.mis.controller.form.*;
import com.mis.db.pojo.RoleEntity;
import com.mis.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/role")
@Tag(name = "RoleController", description = "角色Web接口")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/searchAllRole")
    @Operation(summary = "查询所有角色")
    public ResponseCodeMap searchAllRole() {
        ArrayList<HashMap> list = roleService.searchAllRole();
        return ResponseCodeMap.ok().put("list", list);
    }

    @PostMapping("/searchById")
    @Operation(summary = "根据ID查询角色")
    @SaCheckPermission(value = {"ROOT", "ROLE:SELECT"}, mode = SaMode.OR)
    public ResponseCodeMap searchById(@Valid @RequestBody SearchRoleByIdForm form) {
        HashMap map = roleService.searchById(form.getId());
        return ResponseCodeMap.ok(map);
    }

    @PostMapping("/searchRoleByPage")
    @Operation(summary = "查询角色分页数据")
    @SaCheckPermission(value = {"ROOT", "ROLE:SELECT"}, mode = SaMode.OR)
    public ResponseCodeMap searchRoleByPage(@Valid @RequestBody SearchRoleByPageForm form) {
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        param.put("start", start);
        DataPagingDef pageDef = roleService.searchRoleByPage(param);
        return ResponseCodeMap.ok().put("page", pageDef);
    }

    @PostMapping("/insert")
    @Operation(summary = "添加角色")
    @SaCheckPermission(value = {"ROOT", "ROLE:INSERT"}, mode = SaMode.OR)
    public ResponseCodeMap insert(@Valid @RequestBody InsertRoleForm form) {
        RoleEntity role = new RoleEntity();
        role.setRoleName(form.getRoleName());
        role.setPermissions(JSONUtil.parseArray(form.getPermissions()).toString());
        role.setDesc(form.getDesc());
        int rows = roleService.insert(role);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/update")
    @Operation(summary = "更新角色")
    @SaCheckPermission(value = {"ROOT", "ROLE:UPDATE"}, mode = SaMode.OR)
    public ResponseCodeMap update(@Valid @RequestBody UpdateRoleForm form) {
        RoleEntity role = new RoleEntity();
        role.setId(form.getId());
        role.setRoleName(form.getRoleName());
        role.setPermissions(JSONUtil.parseArray(form.getPermissions()).toString());
        role.setDesc(form.getDesc());
        int rows = roleService.update(role);
        //如果用户修改成功，并且用户修改了该角色的关联权限
        if (rows == 1 && form.getChanged()) {
            //把该角色关联的用户踢下线
            ArrayList<Integer> list = roleService.searchUserIdByRoleId(form.getId());
            list.forEach(userId -> {
                StpUtil.logoutByLoginId(userId);
            });
        }
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/deleteRoleByIds")
    @Operation(summary = "删除角色记录")
    @SaCheckPermission(value = {"ROOT", "ROLE:DELETE"}, mode = SaMode.OR)
    public ResponseCodeMap deleteRoleByIds(@Valid @RequestBody DeleteRoleByIdsForm form) {
        int rows = roleService.deleteRoleByIds(form.getIds());
        return ResponseCodeMap.ok().put("rows", rows);
    }

}

