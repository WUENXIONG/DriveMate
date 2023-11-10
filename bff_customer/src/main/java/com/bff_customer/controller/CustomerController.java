package com.bff_customer.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bff_customer.controller.form.*;
import com.bff_customer.service.CustomerService;
import com.common.util.ResponseCodeMap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping("/customer")
@Tag(name = "CustomerController", description = "客户Web接口")
public class CustomerController {

    @Resource
    private CustomerService customerService;


    @PostMapping("/registerNewCustomer")
    @Operation(summary = "注册新客户")
    public ResponseCodeMap registerNewCustomer(@RequestBody @Valid RegisterNewCustomerForm form) {
        long customerId = customerService.registerNewCustomer(form);
        StpUtil.login(customerId);
        String token = StpUtil.getTokenInfo().getTokenValue();
        return ResponseCodeMap.ok().put("token", token);
    }

    @PostMapping("/login")
    @Operation(summary = "登陆系统")
    public ResponseCodeMap login(@RequestBody @Valid LoginForm form) {
        Long customerId = customerService.login(form);
        if (customerId != null) {
            StpUtil.login(customerId);
            String token = StpUtil.getTokenInfo().getTokenValue();
            return ResponseCodeMap.ok().put("token", token);
        }
        return ResponseCodeMap.ok();
    }

}

