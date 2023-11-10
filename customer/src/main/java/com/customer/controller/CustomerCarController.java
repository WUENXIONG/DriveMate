package com.customer.controller;

import cn.hutool.core.bean.BeanUtil;
import com.common.util.ResponseCodeMap;
import com.customer.controller.form.*;
import com.customer.db.pojo.CustomerCarEntity;
import com.customer.service.CustomerCarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/customer/car")
@Tag(name = "CustomerCarController", description = "客户车辆Web接口")
public class CustomerCarController {
    @Resource
    private CustomerCarService customerCarService;

    @PostMapping("/insertCustomerCar")
    @Operation(summary = "添加客户车辆")
    public ResponseCodeMap insertCustomerCar(@RequestBody @Valid InsertCustomerCarForm form) {
        CustomerCarEntity entity = BeanUtil.toBean(form, CustomerCarEntity.class);
        customerCarService.insertCustomerCar(entity);
        return ResponseCodeMap.ok();
    }

    @PostMapping("/searchCustomerCarList")
    @Operation(summary = "查询客户车辆列表")
    public ResponseCodeMap searchCustomerCarList(@RequestBody @Valid SearchCustomerCarListForm form) {
        ArrayList<HashMap> list = customerCarService.searchCustomerCarList(form.getCustomerId());
        return ResponseCodeMap.ok().put("result", list);
    }

    @PostMapping("/deleteCustomerCarById")
    @Operation(summary = "删除客户车辆")
    public ResponseCodeMap deleteCustomerCarById(@RequestBody @Valid DeleteCustomerCarByIdForm form) {
        int rows = customerCarService.deleteCustomerCarById(form.getId());
        return ResponseCodeMap.ok().put("rows", rows);
    }
}
