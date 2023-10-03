package com.bff_driver.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.map.MapUtil;
import com.bff_driver.controller.form.CreateDriverFaceModelForm;
import com.bff_driver.controller.form.LoginForm;
import com.bff_driver.controller.form.RegisterNewDriverForm;
import com.bff_driver.controller.form.UpdateDriverAuthForm;
import com.bff_driver.service.DriverService;
import com.common.util.ResponseCodeMap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/driver")
@Tag(name = "DriverController", description = "司机模块Web接口")
public class DriverController {
    @Resource
    private DriverService driverService;

    @PostMapping("/registerNewDriver")
    @Operation(summary = "新司机注册")
    public ResponseCodeMap registerNewDriver(@RequestBody @Valid RegisterNewDriverForm form){
        long driverId = driverService.registerNewDriver(form);
        StpUtil.login(driverId);
        String token = StpUtil.getTokenInfo().getTokenValue();
        return ResponseCodeMap.ok().put("token",token);
    }

    @PostMapping("/updateDriverAuth")
    @Operation(summary = "更新实名认证信息")
    @SaCheckLogin
    public ResponseCodeMap updateDriverAuth(@RequestBody @Valid UpdateDriverAuthForm form){
        long driverId = StpUtil.getLoginIdAsLong();
        form.setDriverId(driverId);
        int rows = driverService.updateDriverAuth(form);
        return ResponseCodeMap.ok().put("rows",rows);
    }


    @PostMapping("/createDriverFaceModel")
    @Operation(summary = "创建司机人脸模型归档")
    @SaCheckLogin
    public ResponseCodeMap createDriverFaceModel(@RequestBody @Valid CreateDriverFaceModelForm form){
        long driverId = StpUtil.getLoginIdAsLong();
        form.setDriverId(driverId);
        String result = driverService.createDriverFaceModel(form);
        return ResponseCodeMap.ok().put("result",result);
    }

    @PostMapping("/login")
    @Operation(summary = "登陆系统")
    public ResponseCodeMap login(@RequestBody @Valid LoginForm form) {
        HashMap map = driverService.login(form);
        if (map != null) {
            long driverId = MapUtil.getLong(map, "id");
            byte realAuth = Byte.parseByte(MapUtil.getStr(map, "realAuth"));
            boolean archive = MapUtil.getBool(map, "archive");
            StpUtil.login(driverId);
            String token = StpUtil.getTokenInfo().getTokenValue();

            return ResponseCodeMap.ok().put("token", token).put("realAuth", realAuth).put("archive", archive);
        }
        return ResponseCodeMap.ok();
    }

}
