package com.mis.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.bean.BeanUtil;
import com.common.util.DataPagingDef;
import com.common.util.ResponseCodeMap;
import com.mis.controller.form.*;
import com.mis.db.pojo.DeptEntity;
import com.mis.service.DeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dept")
@Tag(name = "DeptController", description = "部门Web接口")
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping("/searchAllDept")
    @Operation(summary = "查询所有部门")
    public ResponseCodeMap searchAllDept() {
        ArrayList<HashMap> list = deptService.searchAllDept();
        return ResponseCodeMap.ok().put("list", list);
    }

    @PostMapping("/searchById")
    @Operation(summary = "根据ID查询部门")
    @SaCheckPermission(value = {"ROOT", "DEPT:SELECT"}, mode = SaMode.OR)
    public ResponseCodeMap searchById(@Valid @RequestBody SearchDeptByIdForm form) {
        HashMap map = deptService.searchById(form.getId());
        return ResponseCodeMap.ok(map);
    }

    @PostMapping("/searchDeptByPage")
    @Operation(summary = "查询部门分页数据")
    @SaCheckPermission(value = {"ROOT", "DEPT:SELECT"}, mode = SaMode.OR)
    public ResponseCodeMap searchDeptByPage(@Valid @RequestBody SearchDeptByPageForm form) {
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        Map param = BeanUtil.beanToMap(form);
        param.put("start", start);
        DataPagingDef pageDef = deptService.searchDeptByPage(param);
        return ResponseCodeMap.ok().put("page", pageDef);
    }

    @PostMapping("/insert")
    @Operation(summary = "添加部门")
    @SaCheckPermission(value = {"ROOT", "DEPT:INSERT"}, mode = SaMode.OR)
    public ResponseCodeMap insert(@Valid @RequestBody InsertDeptForm form) {
        DeptEntity dept = BeanUtil.toBean(form, DeptEntity.class);
        int rows = deptService.insert(dept);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/update")
    @Operation(summary = "更新部门")
    @SaCheckPermission(value = {"ROOT", "DEPT:UPDATE"}, mode = SaMode.OR)
    public ResponseCodeMap update(@Valid @RequestBody UpdateDeptForm form) {
        DeptEntity dept = new DeptEntity();
        dept.setId(form.getId());
        dept.setDeptName(form.getDeptName());
        dept.setTel(form.getTel());
        dept.setEmail(form.getEmail());
        dept.setDesc(form.getDesc());
        int rows = deptService.update(dept);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/deleteDeptByIds")
    @Operation(summary = "删除部门记录")
    @SaCheckPermission(value = {"ROOT", "DEPT:DELETE"}, mode = SaMode.OR)
    public ResponseCodeMap deleteDeptByIds(@Valid @RequestBody DeleteDeptByIdsForm form) {
        int rows = deptService.deleteDeptByIds(form.getIds());
        return ResponseCodeMap.ok().put("rows", rows);
    }
}