package com.customer.controller;

import cn.hutool.core.bean.BeanUtil;
import com.common.util.ResponseCodeMap;
import com.customer.controller.form.*;
import com.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer")
@Tag(name = "CustomerController", description = "客户Web接口")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @PostMapping("/registerNewCustomer")
    @Operation(summary = "注册新客户")
    public ResponseCodeMap registerNewCustomer(@RequestBody @Valid RegisterNewCustomerForm form) {
        Map param = BeanUtil.beanToMap(form);
        String userId = customerService.registerNewCustomer(param);
        return ResponseCodeMap.ok().put("userId", userId);
    }

    @PostMapping("/login")
    @Operation(summary = "登陆系统")
    public ResponseCodeMap login(@RequestBody @Valid LoginForm form) {
        String userId = customerService.login(form.getCode());
        return ResponseCodeMap.ok().put("userId", userId);
    }

    @PostMapping("/searchCustomerInfoInOrder")
    @Operation(summary = "查询订单中的客户信息")
    public ResponseCodeMap searchCustomerInfoInOrder(@RequestBody @Valid SearchCustomerInfoInOrderForm form) {
        HashMap map = customerService.searchCustomerInfoInOrder(form.getCustomerId());
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/searchCustomerBriefInfo")
    @Operation(summary = "查询客户简明信息")
    public ResponseCodeMap searchCustomerBriefInfo(@RequestBody @Valid SearchCustomerBriefInfoForm form) {
        HashMap map = customerService.searchCustomerBriefInfo(form.getCustomerId());
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/searchCustomerOpenId")
    @Operation(summary = "查询客户的OpenId")
    public ResponseCodeMap searchCustomerOpenId(@RequestBody @Valid SearchCustomerOpenIdForm form) {
        String openId = customerService.searchCustomerOpenId(form.getCustomerId());
        return ResponseCodeMap.ok().put("result", openId);
    }


}

