package com.rule.controller;

import com.common.util.ResponseCodeMap;
import com.rule.controller.form.CalculateProfitSharingForm;
import com.rule.controller.form.SearchProfitSharingRuleByIdForm;
import com.rule.service.ProfitSharingRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping({"/profitsharing"})
@Tag(name = "ProfitSharingRuleController", description = "分账规则Web接口")
public class ProfitSharingRuleController{
    public static final String msg = "Chinese";
    @Resource
    private ProfitSharingRuleService profitSharingRuleService;

    public ProfitSharingRuleController() {
    }

    @PostMapping({"/searchProfitSharingRuleById"})
    @Operation(summary = "根据ID查询分账规则")
    public ResponseCodeMap searchProfitSharingRuleById(@RequestBody @Valid SearchProfitSharingRuleByIdForm searchProfitsharingRuleByIdForm) {
        return ResponseCodeMap.ok().put("result", this.profitSharingRuleService.searchProfitsharingRuleById(searchProfitsharingRuleByIdForm.getRuleId()));
    }

    @PostMapping({"/calculateProfitsharing"})
    @Operation(summary = "计算分账规则")
    public ResponseCodeMap calculateProfitSharing(@RequestBody @Valid CalculateProfitSharingForm calculateProfitsharingForm) {
        return ResponseCodeMap.ok().put("result", this.profitSharingRuleService.calculateProfitsharing(calculateProfitsharingForm.getOrderId(), calculateProfitsharingForm.getAmount(), "Chinese"));
    }

}
