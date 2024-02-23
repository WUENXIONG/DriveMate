package com.order.controller;

import cn.hutool.core.bean.BeanUtil;
import com.common.util.ResponseCodeMap;
import com.order.controller.form.SearchReviewDriverOrderBillForm;
import com.order.controller.form.UpdateBillFeeForm;
import com.order.controller.form.UpdateBillPaymentForm;
import com.order.service.OrderBillService;
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
@RequestMapping("/bill")
@Tag(name = "OrderBillController", description = "订单费用账单Web接口")
public class OrderBillController {
    @Resource
    private OrderBillService orderBillService;

    @PostMapping("/updateBillFee")
    @Operation(summary = "更新订单账单费用")
    public ResponseCodeMap updateBillFee(@RequestBody @Valid UpdateBillFeeForm form) {
        Map param = BeanUtil.beanToMap(form);
        int rows = orderBillService.updateBillFee(param);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/searchReviewDriverOrderBill")
    @Operation(summary = "查询司机预览账单")
    public ResponseCodeMap searchReviewDriverOrderBill(@RequestBody @Valid SearchReviewDriverOrderBillForm form) {
        Map param = BeanUtil.beanToMap(form);
        HashMap map = orderBillService.searchReviewDriverOrderBill(param);
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/updateBillPayment")
    @Operation(summary = "更新账单实际支付费用")
    public ResponseCodeMap updateBillPayment(@RequestBody @Valid UpdateBillPaymentForm form) {
        Map param = BeanUtil.beanToMap(form);
        int rows = orderBillService.updateBillPayment(param);
        return ResponseCodeMap.ok().put("rows", rows);
    }


}

