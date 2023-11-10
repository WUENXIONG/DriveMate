package com.rule.controller;

import com.common.util.ResponseCodeMap;
import com.rule.controller.form.CalculateOrderChargeForm;
import com.rule.controller.form.EstimateOrderChargeForm;
import com.rule.controller.form.SearchChargeRuleByIdForm;
import com.rule.service.ChargeRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping({"/charge"})
@Tag(name = "ChargeRuleController", description = "代驾费用的Web接口")
public class ChargeRuleController {
    public static final String msg = "Chinese";
    @Resource
    private ChargeRuleService chargeRuleService;

    public ChargeRuleController() {
    }

    @PostMapping({"/searchChargeRuleById"})
    @Operation(summary = "根据ID查询费用规则")
    public ResponseCodeMap searchChargeRuleById(@RequestBody @Valid SearchChargeRuleByIdForm searchChargeRuleByIdForm) {
        return ResponseCodeMap.ok().put("result", this.chargeRuleService.searchChargeRuleById(searchChargeRuleByIdForm.getRuleId()));
    }

    @PostMapping({"/calculateOrderCharge"})
    @Operation(
            summary = "计算代驾费用"
    )
    public ResponseCodeMap calculateOrderCharge(@RequestBody @Valid CalculateOrderChargeForm calculateOrderChargeForm) {
        return ResponseCodeMap.ok().put("result", this.chargeRuleService.calculateOrderCharge(calculateOrderChargeForm.getMileage(), calculateOrderChargeForm.getTime(), calculateOrderChargeForm.getMinute(), "Chinese"));
    }

    @PostMapping({"/estimateOrderCharge"})
    public ResponseCodeMap estimateOrderCharge(@RequestBody @Valid EstimateOrderChargeForm estimateOrderChargeForm) {
        return ResponseCodeMap.ok().put("result", this.chargeRuleService.calculateOrderCharge(estimateOrderChargeForm.getMileage(), estimateOrderChargeForm.getTime(), 0, "Chinese"));
    }
}
