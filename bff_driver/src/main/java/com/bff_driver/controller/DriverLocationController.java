package com.bff_driver.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.bff_driver.controller.form.UpdateOrderLocationCacheForm;
import com.bff_driver.service.DriverLocationService;
import com.common.util.ResponseCodeMap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bff_driver.controller.form.UpdateLocationCacheForm;


import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/driver/location")
@Tag(name = "DriverLocationController", description = "司机定位服务Web接口")
public class DriverLocationController {
    @Resource
    private DriverLocationService locationService;

    @PostMapping("/updateLocationCache")
    @Operation(summary = "更新司机缓存GPS定位")
    @SaCheckLogin
    public ResponseCodeMap updateLocationCache(@RequestBody @Valid UpdateLocationCacheForm form) {
        long driverId = StpUtil.getLoginIdAsLong();
        form.setDriverId(driverId);
        locationService.updateLocationCache(form);
        return ResponseCodeMap.ok();
    }

    @PostMapping("/updateOrderLocationCache")
    @Operation(summary = "更新订单定位缓存")
    @SaCheckLogin
    public ResponseCodeMap updateOrderLocationCache(@RequestBody @Valid UpdateOrderLocationCacheForm form) {
        locationService.updateOrderLocationCache(form);
        return ResponseCodeMap.ok();
    }


}
