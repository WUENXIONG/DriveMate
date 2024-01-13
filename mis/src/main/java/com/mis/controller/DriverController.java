package com.mis.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.common.util.*;
import com.mis.controller.form.SearchDriverByPageForm;
import com.mis.controller.form.SearchDriverComprehensiveDataForm;
import com.mis.controller.form.UpdateDriverRealAuthForm;
import com.mis.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;


@RestController
@RequestMapping("/driver")
@Tag(name = "DriverController", description = "司机管理Web接口")
public class DriverController {

    @Resource
    private DriverService driverService;

    @PostMapping("/searchDriverByPage")
    @SaCheckPermission(value = {"ROOT", "DRIVER:SELECT"}, mode = SaMode.OR)
    @Operation(summary = "查询司机分页记录")
    public ResponseCodeMap searchDriverByPage(@RequestBody @Valid SearchDriverByPageForm form) {
        DataPaging dataPaging = driverService.searchDriverByPage(form);
        return ResponseCodeMap.ok().put("result", dataPaging);
    }

    @PostMapping("/searchDriverComprehensiveData")
    @SaCheckPermission(value = {"ROOT", "DRIVER:SELECT"}, mode = SaMode.OR)
    @Operation(summary = "查询司机综合数据")
    public ResponseCodeMap searchDriverComprehensiveData(@RequestBody @Valid SearchDriverComprehensiveDataForm form) {
        HashMap map = driverService.searchDriverComprehensiveData(form.getRealAuth(), form.getDriverId());
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/updateDriverRealAuth")
    @SaCheckPermission(value = {"ROOT", "DRIVER:UPDATE"}, mode = SaMode.OR)
    @Operation(summary = "更新司机实名认证状态")
    public ResponseCodeMap updateDriverRealAuth(@RequestBody @Valid UpdateDriverRealAuthForm form) {
        int rows = driverService.updateDriverRealAuth(form);
        return ResponseCodeMap.ok().put("rows", rows);
    }

}

