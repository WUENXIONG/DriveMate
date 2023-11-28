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

    @PostMapping("/searchDriverCurrentOrder")
    @Operation(summary = "查询司机当前订单")
    public ResponseCodeMap searchDriverCurrentOrder(@RequestBody @Valid SearchDriverCurrentOrderForm form) {
        HashMap map = orderService.searchDriverCurrentOrder(form.getDriverId());
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/hasCustomerCurrentOrder")
    @Operation(summary = "查询乘客是否存在当前的订单")
    public ResponseCodeMap hasCustomerCurrentOrder(@RequestBody @Valid HasCustomerCurrentOrderForm form) {
        HashMap map = orderService.hasCustomerCurrentOrder(form.getCustomerId());
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/searchOrderForMoveById")
    @Operation(summary = "查询订单信息用于司乘同显功能")
    public ResponseCodeMap searchOrderForMoveById(@RequestBody @Valid SearchOrderForMoveByIdForm form) {
        Map param = BeanUtil.beanToMap(form);
        HashMap map = orderService.searchOrderForMoveById(param);
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/arriveStartPlace")
    @Operation(summary = "司机到达上车点")
    public ResponseCodeMap arriveStartPlace(@RequestBody @Valid ArriveStartPlaceForm form) {
        Map param = BeanUtil.beanToMap(form);
        param.put("status", 3);
        int rows = orderService.arriveStartPlace(param);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/confirmArriveStartPlace")
    @Operation(summary = "乘客确认司机到达上车点")
    public ResponseCodeMap confirmArriveStartPlace(@RequestBody @Valid ConfirmArriveStartPlaceForm form) {
        boolean result = orderService.confirmArriveStartPlace(form.getOrderId());
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/startDriving")
    @Operation(summary = "开始代驾")
    public ResponseCodeMap startDriving(@RequestBody @Valid StartDrivingForm form) {
        Map param = BeanUtil.beanToMap(form);
        param.put("status", 4);
        int rows = orderService.startDriving(param);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/updateOrderStatus")
    @Operation(summary = "更新订单状态")
    public ResponseCodeMap updateOrderStatus(@RequestBody @Valid UpdateOrderStatusForm form) {
        Map param = BeanUtil.beanToMap(form);
        int rows = orderService.updateOrderStatus(param);
        return ResponseCodeMap.ok().put("rows", rows);
    }


}

