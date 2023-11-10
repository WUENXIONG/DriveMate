package com.map.controller;

import cn.hutool.core.bean.BeanUtil;
import com.common.util.ResponseCodeMap;
import com.map.controller.form.RemoveLocationCacheForm;
import com.map.controller.form.SearchBefittingDriverAboutOrderForm;
import com.map.controller.form.UpdateLocationCacheForm;
import com.map.service.DriverLocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/driver/location")
@Tag(name = "DriverLocationController", description = "司机定位服务Web接口")
public class DriverLocationController {

    @Resource
    private DriverLocationService driverLocationService;

    @PostMapping("/updateLocationCache")
    @Operation(summary = "更新司机GPS定位缓存")
    public ResponseCodeMap updateLocationCache(@RequestBody @Valid UpdateLocationCacheForm form) {
        //更新新的定位缓存
        Map param = BeanUtil.beanToMap(form);
        driverLocationService.updateLocationCache(param);
        return ResponseCodeMap.ok();
    }

    @PostMapping("/removeLocationCache")
    @Operation(summary = "删除司机GPS定位缓存")
    public ResponseCodeMap removeLocationCache(@RequestBody @Valid RemoveLocationCacheForm form) {
        driverLocationService.removeLocationCache(form.getDriverId());
        return ResponseCodeMap.ok();
    }

    @PostMapping("/searchBefittingDriverAboutOrder")
    @Operation(summary = "查询符合某个订单接单的司机列表")
    public ResponseCodeMap searchBefittingDriverAboutOrder(@RequestBody @Valid SearchBefittingDriverAboutOrderForm form) {
        double startPlaceLatitude = Double.parseDouble(form.getStartPlaceLatitude());
        double startPlaceLongitude = Double.parseDouble(form.getStartPlaceLongitude());
        double endPlaceLatitude = Double.parseDouble(form.getEndPlaceLatitude());
        double endPlaceLongitude = Double.parseDouble(form.getEndPlaceLongitude());
        double mileage = Double.parseDouble(form.getMileage());
        ArrayList list = driverLocationService.searchBefittingDriverAboutOrder(startPlaceLatitude, startPlaceLongitude, endPlaceLatitude, endPlaceLongitude, mileage);
        return ResponseCodeMap.ok().put("result", list);
    }

}

