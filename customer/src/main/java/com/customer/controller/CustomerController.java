package com.customer.controller;

import cn.hutool.core.bean.BeanUtil;
import com.common.util.ResponseCodeMap;
import com.customer.controller.form.LoginForm;
import com.customer.controller.form.RegisterNewCustomerForm;
import com.customer.controller.form.SearchCustomerInfoInOrderForm;
import com.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

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

}

