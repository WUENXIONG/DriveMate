package com.order.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.common.util.ResponseCodeMap;
import com.order.controller.form.*;
import com.order.db.pojo.OrderBillEntity;
import com.order.db.pojo.OrderEntity;
import com.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Tag(name = "OrderController", description = "订单模块Web接口")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/searchDriverTodayBusinessData")
    @Operation(summary = "查询司机当天营业数据")
    public ResponseCodeMap searchDriverTodayBusinessData(@RequestBody @Valid SearchDriverTodayBusinessDataForm form) {
        HashMap result = orderService.searchDriverTodayBusinessData(form.getDriverId());
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/insertOrder")
    @Operation(summary = "顾客下单")
    public ResponseCodeMap insertOrder(@RequestBody @Valid InsertOrderForm form) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUuid(form.getUuid());
        orderEntity.setCustomerId(form.getCustomerId());
        orderEntity.setStartPlace(form.getStartPlace());
        JSONObject json = new JSONObject();
        json.set("latitude", form.getStartPlaceLatitude());
        json.set("longitude", form.getStartPlaceLongitude());
        orderEntity.setStartPlaceLocation(json.toString());
        orderEntity.setEndPlace(form.getEndPlace());
        json = new JSONObject();
        json.set("latitude", form.getEndPlaceLatitude());
        json.set("longitude", form.getEndPlaceLongitude());
        orderEntity.setEndPlaceLocation(json.toString());
        orderEntity.setExpectsMileage(new BigDecimal(form.getExpectsMileage()));
        orderEntity.setExpectsFee(new BigDecimal(form.getExpectsFee()));
        orderEntity.setFavourFee(new BigDecimal(form.getFavourFee()));
        orderEntity.setChargeRuleId(form.getChargeRuleId());
        orderEntity.setCarPlate(form.getCarPlate());
        orderEntity.setCarType(form.getCarType());
        orderEntity.setDate(form.getDate());

        OrderBillEntity billEntity = new OrderBillEntity();
        billEntity.setBaseMileage(form.getBaseMileage());
        billEntity.setBaseMileagePrice(new BigDecimal(form.getBaseMileagePrice()));
        billEntity.setExceedMileagePrice(new BigDecimal(form.getExceedMileagePrice()));
        billEntity.setBaseMinute(form.getBaseMinute());
        billEntity.setExceedMinutePrice(new BigDecimal(form.getExceedMinutePrice()));
        billEntity.setBaseReturnMileage(form.getBaseReturnMileage());
        billEntity.setExceedReturnPrice(new BigDecimal(form.getExceedReturnPrice()));

        String id = orderService.insertOrder(orderEntity, billEntity);
        return ResponseCodeMap.ok().put("result", id);
    }


    @PostMapping("/acceptNewOrder")
    @Operation(summary = "司机接单")
    public ResponseCodeMap acceptNewOrder(@RequestBody @Valid AcceptNewOrderForm form) {
        String result = orderService.acceptNewOrder(form.getDriverId(), form.getOrderId());
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/searchDriverExecuteOrder")
    @Operation(summary = "查询司机正在执行的订单记录")
    public ResponseCodeMap searchDriverExecuteOrder(@RequestBody @Valid SearchDriverExecuteOrderForm form) {
        Map param = BeanUtil.beanToMap(form);
        HashMap map = orderService.searchDriverExecuteOrder(param);
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/searchOrderStatus")
    @Operation(summary = "查询订单状态")
    public ResponseCodeMap searchOrderStatus(@RequestBody @Valid SearchOrderStatusForm form) {
        Map param = BeanUtil.beanToMap(form);
        Integer status = orderService.searchOrderStatus(param);
        return ResponseCodeMap.ok().put("result", status);
    }

    @PostMapping("/deleteUnAcceptOrder")
    @Operation(summary = "删除没有司机接单的订单")
    public ResponseCodeMap deleteUnAcceptOrder(@RequestBody @Valid DeleteUnAcceptOrderForm form) {
        Map param = BeanUtil.beanToMap(form);
        String result = orderService.deleteUnAcceptOrder(param);
        return ResponseCodeMap.ok().put("result", result);
    }


}

