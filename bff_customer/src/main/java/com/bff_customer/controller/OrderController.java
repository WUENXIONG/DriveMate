package com.bff_customer.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.bff_customer.controller.form.CreateNewOrderForm;
import com.bff_customer.controller.form.SearchOrderStatusForm;
import com.bff_customer.controller.form.DeleteUnAcceptOrderForm;
import com.bff_customer.service.OrderService;
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

    @PostMapping("/createNewOrder")
    @Operation(summary = "创建新订单")
    @SaCheckLogin
    public ResponseCodeMap createNewOrder(@RequestBody @Valid CreateNewOrderForm form) {
        Long customerId = StpUtil.getLoginIdAsLong();
        form.setCustomerId(customerId);
        HashMap result = orderService.createNewOrder(form);
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/searchOrderStatus")
    @Operation(summary = "查询订单状态")
    @SaCheckLogin
    public ResponseCodeMap searchOrderStatus(@RequestBody @Valid SearchOrderStatusForm form) {
        long customerId = StpUtil.getLoginIdAsLong();
        form.setCustomerId(customerId);
        Integer status = orderService.searchOrderStatus(form);
        return ResponseCodeMap.ok().put("result", status);
    }

    @PostMapping("/deleteUnAcceptOrder")
    @Operation(summary = "关闭没有司机接单的订单")
    @SaCheckLogin
    public ResponseCodeMap deleteUnAcceptOrder(@RequestBody @Valid DeleteUnAcceptOrderForm form) {
        long customerId = StpUtil.getLoginIdAsLong();
        form.setCustomerId(customerId);

        String result = orderService.deleteUnAcceptOrder(form);
        return ResponseCodeMap.ok().put("result", result);
    }

}

