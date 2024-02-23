package com.bff_driver.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.bff_driver.controller.form.*;
import com.bff_driver.service.OrderService;
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
@RequestMapping("/order")
@Tag(name = "OrderController", description = "订单模块Web接口")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/acceptNewOrder")
    @SaCheckLogin
    @Operation(summary = "司机接单")
    public ResponseCodeMap acceptNewOrder(@RequestBody @Valid AcceptNewOrderForm form) {
        long driverId = StpUtil.getLoginIdAsLong();
        form.setDriverId(driverId);
        String result = orderService.acceptNewOrder(form);
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/searchDriverExecuteOrder")
    @SaCheckLogin
    @Operation(summary = "查询司机正在执行的订单记录")
    public ResponseCodeMap searchDriverExecuteOrder(@RequestBody @Valid SearchDriverExecuteOrderForm form) {
        long driverId = StpUtil.getLoginIdAsLong();
        form.setDriverId(driverId);
        HashMap map = orderService.searchDriverExecuteOrder(form);
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/searchDriverCurrentOrder")
    @SaCheckLogin
    @Operation(summary = "查询司机当前订单")
    public ResponseCodeMap searchDriverCurrentOrder() {
        long driverId = StpUtil.getLoginIdAsLong();
        SearchDriverCurrentOrderForm form = new SearchDriverCurrentOrderForm();
        form.setDriverId(driverId);
        HashMap map = orderService.searchDriverCurrentOrder(form);
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/searchOrderForMoveById")
    @SaCheckLogin
    @Operation(summary = "查询订单信息用于司乘同显功能")
    public ResponseCodeMap searchOrderForMoveById(@RequestBody @Valid SearchOrderForMoveByIdForm form) {
        long driverId = StpUtil.getLoginIdAsLong();
        form.setDriverId(driverId);
        HashMap map = orderService.searchOrderForMoveById(form);
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/arriveStartPlace")
    @Operation(summary = "司机到达上车点")
    @SaCheckLogin
    public ResponseCodeMap arriveStartPlace(@RequestBody @Valid ArriveStartPlaceForm form) {
        long driverId = StpUtil.getLoginIdAsLong();
        form.setDriverId(driverId);
        int rows = orderService.arriveStartPlace(form);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/startDriving")
    @Operation(summary = "开始代驾")
    @SaCheckLogin
    public ResponseCodeMap startDriving(@RequestBody @Valid StartDrivingForm form) {
        long driverId = StpUtil.getLoginIdAsLong();
        form.setDriverId(driverId);
        int rows = orderService.startDriving(form);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/updateOrderStatus")
    @SaCheckLogin
    @Operation(summary = "更新订单状态")
    public ResponseCodeMap updateOrderStatus(@RequestBody @Valid UpdateOrderStatusForm form) {
        int rows = orderService.updateOrderStatus(form);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/updateBillFee")
    @SaCheckLogin
    @Operation(summary = "更新订单账单费用")
    public ResponseCodeMap updateBillFee(@RequestBody @Valid UpdateBillFeeForm form) {
        long driverId = StpUtil.getLoginIdAsLong();
        form.setDriverId(driverId);
        int rows = orderService.updateOrderBill(form);
        return ResponseCodeMap.ok().put("rows", rows);
    }


    @PostMapping("/searchReviewDriverOrderBill")
    @SaCheckLogin
    @Operation(summary = "查询司机预览订单")
    public ResponseCodeMap searchReviewDriverOrderBill(@RequestBody @Valid SearchReviewDriverOrderBillForm form) {
        long driverId = StpUtil.getLoginIdAsLong();
        form.setDriverId(driverId);
        HashMap map = orderService.searchReviewDriverOrderBill(form);
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/searchOrderStatus")
    @SaCheckLogin
    @Operation(summary = "查询订单状态")
    public ResponseCodeMap searchOrderStatus(@RequestBody @Valid SearchOrderStatusForm form) {
        long driverId = StpUtil.getLoginIdAsLong();
        form.setDriverId(driverId);
        Integer status = orderService.searchOrderStatus(form);
        return ResponseCodeMap.ok().put("result", status);
    }

    @PostMapping("/updateOrderAboutPayment")
    @SaCheckLogin
    @Operation(summary = "更新订单相关的付款信息")
    public ResponseCodeMap updateOrderAboutPayment(@RequestBody @Valid UpdateOrderAboutPaymentForm form) {
        long driverId = StpUtil.getLoginIdAsLong();
        String result = orderService.updateOrderAboutPayment(driverId, form);
        return ResponseCodeMap.ok().put("result", result);
    }


}

