package com.driver.controller;

import com.common.util.ResponseCodeMap;
import com.driver.controller.form.SearchDriverSettingsForm;
import com.driver.service.DriverSettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/settings")
@Tag(name = "SettingsController", description = "司机设置模块Web接口")
public class DriverSettingsController {
    @Resource
    private DriverSettingsService settingsService;

    @PostMapping("/searchDriverSettings")
    @Operation(summary = "查询司机的设置")
    public ResponseCodeMap searchDriverSettings(@RequestBody @Valid SearchDriverSettingsForm form) {
        HashMap map = settingsService.searchDriverSettings(form.getDriverId());
        return ResponseCodeMap.ok().put("result", map);
    }
}
