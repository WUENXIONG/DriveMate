package com.driver.controller;

import cn.hutool.core.bean.BeanUtil;
import com.common.util.DataPaging;
import com.common.util.ResponseCodeMap;
import com.driver.controller.form.*;
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

    @PostMapping("/searchDriverBaseInfo")
    @Operation(summary = "查询司机基本信息")
    public ResponseCodeMap searchDriverBaseInfo(@RequestBody @Valid SearchDriverBaseInfoForm form) {
        HashMap result = driverService.searchDriverBaseInfo(form.getDriverId());
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/searchDriverByPage")
    @Operation(summary = "查询司机分页记录")
    public ResponseCodeMap searchDriverByPage(@RequestBody @Valid SearchDriverByPageForm form) {
        Map param = BeanUtil.beanToMap(form);
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        param.put("start", start);
        DataPaging dataPaging = driverService.searchDriverByPage(param);
        return ResponseCodeMap.ok().put("result", dataPaging);
    }

    @PostMapping("/searchDriverAuth")
    @Operation(summary = "查询司机认证信息")
    public ResponseCodeMap searchDriverAuth(@RequestBody @Valid SearchDriverAuthForm form) {
        HashMap result = driverService.searchDriverAuth(form.getDriverId());
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/searchDriverRealSummary")
    @Operation(summary = "查询司机实名信息摘要")
    public ResponseCodeMap searchDriverRealSummary(@RequestBody @Valid SearchDriverRealSummaryForm form) {
        HashMap map = driverService.searchDriverRealSummary(form.getDriverId());
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/updateDriverRealAuth")
    @Operation(summary = "更新司机实名认证状态")
    public ResponseCodeMap updateDriverRealAuth(@RequestBody @Valid UpdateDriverRealAuthForm form) {
        Map param = BeanUtil.beanToMap(form);
        int rows = driverService.updateDriverRealAuth(param);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/searchDriverBriefInfo")
    @Operation(summary = "查询司机简明信息")
    public ResponseCodeMap searchDriverBriefInfo(@RequestBody @Valid SearchDriverBriefInfoForm form) {
        HashMap map = driverService.searchDriverBriefInfo(form.getDriverId());
        return ResponseCodeMap.ok().put("result", map);
    }


}
