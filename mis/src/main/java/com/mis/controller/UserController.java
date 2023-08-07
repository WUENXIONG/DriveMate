package com.mis.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.common.util.DataPagingDef;
import com.common.util.ResponseCodeMap;
import com.mis.controller.form.*;
import com.mis.db.pojo.UserEntity;
import com.mis.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "用户Web接口")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 生成登陆二维码的字符串
     */
    @GetMapping("/createQrCode")
    @Operation(summary = "生成二维码Base64格式的字符串")
    public ResponseCodeMap createQrCode() {
        HashMap map = userService.createQrCode();
        return ResponseCodeMap.ok(map);
    }

    /**
     * 登陆成功后加载用户的基本信息
     */
    @GetMapping("/loadUserInfo")
    @Operation(summary = "登陆成功后加载用户的基本信息")
    @SaCheckLogin
    public ResponseCodeMap loadUserInfo() {
        int userId = StpUtil.getLoginIdAsInt();
        HashMap summary = userService.searchUserSummary(userId);
        return ResponseCodeMap.ok(summary);
    }

    @PostMapping("/searchById")
    @Operation(summary = "根据ID查找用户")
    @SaCheckPermission(value = {"ROOT", "USER:SELECT"}, mode = SaMode.OR)
    public ResponseCodeMap searchById(@Valid @RequestBody SearchUserByIdForm form) {
        HashMap map = userService.searchById(form.getUserId());
        return ResponseCodeMap.ok(map);
    }

    @GetMapping("/searchAllUser")
    @Operation(summary = "查询所有用户")
    @SaCheckLogin
    public ResponseCodeMap searchAllUser() {
        ArrayList<HashMap> list = userService.searchAllUser();
        return ResponseCodeMap.ok().put("list", list);
    }

    @PostMapping("/login")
    @Operation(summary = "登陆系统")
    public ResponseCodeMap login(@Valid @RequestBody LoginForm form) {
        Map param = BeanUtil.beanToMap(form);
        Integer userId = userService.login(param);
        ResponseCodeMap r = ResponseCodeMap.ok().put("result", userId != null ? true : false);
        if (userId != null) {
            StpUtil.setLoginId(userId);
//            StpUtil.login(userId);
            Set<String> permissions = userService.searchUserPermissions(userId);
            String token = StpUtil.getTokenInfo().getTokenValue();
            r.put("permissions", permissions).put("token", token);
        }
        return r;
    }

    @GetMapping("/logout")
    @Operation(summary = "退出系统")
    public ResponseCodeMap logout() {
        StpUtil.logout();
        return ResponseCodeMap.ok();
    }

    @PostMapping("/updatePassword")
    @SaCheckLogin
    @Operation(summary = "修改密码")
    public ResponseCodeMap updatePassword(@Valid @RequestBody UpdatePasswordForm form) {
        int userId = StpUtil.getLoginIdAsInt();
        Map param = BeanUtil.beanToMap(form);
        param.put("userId", userId);
        int rows = userService.updatePassword(param);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/searchUserByPage")
    @Operation(summary = "查询用户分页记录")
    @SaCheckPermission(value = {"ROOT", "USER:SELECT"}, mode = SaMode.OR)
    public ResponseCodeMap searchUserByPage(@Valid @RequestBody SearchUserByPageForm form) {
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        Map param = BeanUtil.beanToMap(form);
        param.put("start", start);
        DataPagingDef pageDef = userService.searchUserByPage(param);
        return ResponseCodeMap.ok().put("page", pageDef);
    }

    @PostMapping("/insert")
    @SaCheckPermission(value = {"ROOT", "USER:INSERT"}, mode = SaMode.OR)
    @Operation(summary = "添加用户")
    public ResponseCodeMap insert(@Valid @RequestBody InsertUserForm form) {
        UserEntity user = BeanUtil.toBean(form, UserEntity.class);
        user.setStatus((byte) 1);
        user.setRole(JSONUtil.parseArray(form.getRole()).toString());
        user.setCreateTime(new DateTime());
        int rows = userService.insert(user);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/update")
    @SaCheckPermission(value = {"ROOT", "USER:UPDATE"}, mode = SaMode.OR)
    @Operation(summary = "修改用户")
    public ResponseCodeMap update(@Valid @RequestBody UpdateUserForm form) {
        Map param = BeanUtil.beanToMap(form);
        param.replace("role", JSONUtil.parseArray(form.getRole()).toString());
        int rows = userService.update(param);
        if (rows == 1) {
            StpUtil.logout(form.getUserId());
        }
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/deleteUserByIds")
    @SaCheckPermission(value = {"ROOT", "USER:DELETE"}, mode = SaMode.OR)
    @Operation(summary = "删除用户")
    public ResponseCodeMap deleteUserByIds(@Valid @RequestBody DeleteUserByIdsForm form) {
        Integer userId = StpUtil.getLoginIdAsInt();
        if (ArrayUtil.contains(form.getIds(), userId)) {
            return ResponseCodeMap.error("您不能删除自己的帐户");
        }
        int rows = userService.deleteUserByIds(form.getIds());
        if (rows > 0) {
            for (Integer id : form.getIds()) {
                StpUtil.logout(id);
            }
        }
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/searchNameAndDept")
    @Operation(summary = "查找员工姓名和部门")
    @SaCheckLogin
    public ResponseCodeMap searchNameAndDept(@Valid @RequestBody SearchNameAndDeptForm form) {
        HashMap map = userService.searchNameAndDept(form.getId());
        return ResponseCodeMap.ok(map);
    }

}

