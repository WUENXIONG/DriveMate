package com.rule.controller;

import com.common.util.ResponseCodeMap;
import com.rule.controller.form.CalculateDriverCancelOrderForm;
import com.rule.controller.form.SearchCancelRuleByIdForm;
import com.rule.service.CancelRuleService;
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
@RequestMapping({"/cancel"})
@Tag(name = "CancelRuleController", description = "订单取消的Web接口")
public class CancelRuleController {
    public static final String msg = "Chinese";
    @Resource
    private CancelRuleService cancelRuleService;

    public CancelRuleController() {
    }

    @PostMapping({"/calculateDriverCancelOrder"})
    @Operation(summary = "计算司机取消订单")
    public ResponseCodeMap calculateDriverCancelOrder(@RequestBody @Valid CalculateDriverCancelOrderForm a) {
        String status = a.getStatus();
        short cancelNum = a.getCancelNum();
        short acceptMinute = a.getAcceptMinute();
        short waitingMinute = a.getWaitingMinute();
        HashMap var7 = this.cancelRuleService.calculateDriverCancelOrder(status, cancelNum, acceptMinute, waitingMinute, "Chinese");
        return ResponseCodeMap.ok().put("result", var7);
    }

    @PostMapping({"/searchCancelRuleById"})
    @Operation(
            summary = "根据ID查询取消规则"
    )
    public ResponseCodeMap searchCancelRuleById(@RequestBody @Valid SearchCancelRuleByIdForm searchCancelRuleByIdForm) {
        return ResponseCodeMap.ok().put("result", this.cancelRuleService.searchCancelRuleById(searchCancelRuleByIdForm.getRuleId()));
    }
}
