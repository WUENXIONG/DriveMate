package com.bff_customer.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bff_customer.controller.form.*;
import com.bff_customer.service.CustomerCarService;
import com.common.util.ResponseCodeMap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/customer/car")
@Tag(name = "CustomerController", description = "客户车辆Web接口")
public class CustomerCarController {
    @Resource
    private CustomerCarService customerCarService;

    @PostMapping("/insertCustomerCar")
    @Operation(summary = "添加客户车辆")
    public ResponseCodeMap insertCustomerCar(@RequestBody @Valid InsertCustomerCarForm form) {
        long customerId = StpUtil.getLoginIdAsLong();
        form.setCustomerId(customerId);
        customerCarService.insertCustomerCar(form);
        return ResponseCodeMap.ok();
    }

    @PostMapping("/searchCustomerCarList")
    @Operation(summary = "查询客户车辆列表")
    public ResponseCodeMap searchCustomerCarList(@RequestBody @Valid SearchCustomerCarListForm form) {
        long customerId = StpUtil.getLoginIdAsLong();
        form.setCustomerId(customerId);
        ArrayList<HashMap> list = customerCarService.searchCustomerCarList(form);
        return ResponseCodeMap.ok().put("result", list);
    }

    @PostMapping("/deleteCustomerCarById")
    @Operation(summary = "删除客户车辆")
    public ResponseCodeMap deleteCustomerCarById(@RequestBody @Valid DeleteCustomerCarByIdForm form) {
        int rows = customerCarService.deleteCustomerCarById(form);
        return ResponseCodeMap.ok().put("rows", rows);
    }
}

