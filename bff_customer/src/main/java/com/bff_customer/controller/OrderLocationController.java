package com.bff_customer.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.bff_customer.controller.form.SearchOrderLocationCacheForm;
import com.bff_customer.service.OrderLocationService;
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
@RequestMapping("/order/location")
@Tag(name = "OrderLocationController", description = "订单定位服务Web接口")
public class OrderLocationController {

    @Resource
    private OrderLocationService orderLocationService;

    @PostMapping("/searchOrderLocationCache")
    @Operation(summary = "查询订单定位缓存")
    @SaCheckLogin
    public ResponseCodeMap searchOrderLocationCache(@RequestBody @Valid SearchOrderLocationCacheForm form) {
        HashMap map = orderLocationService.searchOrderLocationCache(form);
        return ResponseCodeMap.ok().put("result", map);
    }

}
