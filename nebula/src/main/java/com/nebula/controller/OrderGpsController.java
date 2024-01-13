package com.nebula.controller;

import com.common.util.ResponseCodeMap;
import com.nebula.controller.form.CalculateOrderMileageForm;
import com.nebula.controller.form.InsertOrderGpsForm;
import com.nebula.controller.form.SearchOrderGpsForm;
import com.nebula.controller.form.SearchOrderLastGpsForm;
import com.nebula.service.OrderGpsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/order/gps")
@Tag(name = "OrderGpsController", description = "订单GPS记录Web接口")
public class OrderGpsController {
    @Resource
    private OrderGpsService orderGpsService;

    @PostMapping("/insertOrderGps")
    @Operation(summary = "添加订单GPS记录")
    public ResponseCodeMap insertOrderGps(@RequestBody @Valid InsertOrderGpsForm form) {
        int rows = orderGpsService.insertOrderGps(form.getList());
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/searchOrderGps")
    @Operation(summary = "获取某个订单所有的GPS定位")
    public ResponseCodeMap searchOrderGps(@RequestBody @Valid SearchOrderGpsForm form) {
        ArrayList<HashMap> list = orderGpsService.searchOrderGps(form.getOrderId());
        return ResponseCodeMap.ok().put("result", list);
    }

    @PostMapping("/searchOrderLastGps")
    @Operation(summary = "获取某个订单最后的GPS定位")
    public ResponseCodeMap searchOrderLastGps(@RequestBody @Valid SearchOrderLastGpsForm form) {
        HashMap map = orderGpsService.searchOrderLastGps(form.getOrderId());
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/calculateOrderMileage")
    @Operation(summary = "计算订单里程")
    public ResponseCodeMap calculateOrderMileage(@RequestBody @Valid CalculateOrderMileageForm form) {
        String mileage = orderGpsService.calculateOrderMileage(form.getOrderId());
        return ResponseCodeMap.ok().put("result", mileage);
    }


}

