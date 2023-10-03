package com.driver.controller;

import cn.hutool.core.bean.BeanUtil;
import com.common.util.ResponseCodeMap;
import com.driver.controller.form.CreateDriverFaceModelForm;
import com.driver.controller.form.LoginForm;
import com.driver.controller.form.RegisterNewDriverForm;
import com.driver.controller.form.UpdateDriverAuthForm;
import com.driver.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/driver")
@Tag(name = "DriverController", description = "司机模块Web接口")
public class DriverController {
    @Resource
    private DriverService driverService;

    @PostMapping("/registerNewDriver")
    @Operation(summary = "新司机注册")
    public ResponseCodeMap registerNewDriver(@RequestBody @Valid RegisterNewDriverForm form){
        Map param = BeanUtil.beanToMap(form);
        String userId = driverService.registerNewDriver(param);
        return ResponseCodeMap.ok().put("userId",userId);
    }

    @PostMapping("/updateDriverAuth")
    @Operation(summary = "更新实名认证信息")
    public ResponseCodeMap updateDriverAuth(@RequestBody @Valid UpdateDriverAuthForm form) {
        Map param = BeanUtil.beanToMap(form);
        int rows = driverService.updateDriverAuth(param);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/createDriverFaceModel")
    @Operation(summary = "创建司机人脸模型归档")
    public ResponseCodeMap createDriverFaceModel(@RequestBody @Valid CreateDriverFaceModelForm form){
        String result = driverService.createDriverFaceModel(form.getDriverId(), form.getPhoto());
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/login")
    @Operation(summary = "登陆系统")
    public ResponseCodeMap login(@RequestBody @Valid LoginForm form) {
        HashMap map = driverService.login(form.getCode());
        return ResponseCodeMap.ok().put("result", map);
    }


}
