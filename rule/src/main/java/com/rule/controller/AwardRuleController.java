package com.rule.controller;

import com.common.util.ResponseCodeMap;
import com.rule.controller.form.CalculateIncentiveFeeForm;
import com.rule.service.AwardRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping({"/award"})
@Tag(name = "AwardRuleController", description = "系统奖励规则Web接口")
public class AwardRuleController {
    @Resource
    private AwardRuleService awardRuleService;
    public static final String msg = "Chinese";

    public AwardRuleController() {
    }

    @PostMapping({"/calculateIncentiveFee"})
    @Operation(summary = "计算系统奖励")
    public ResponseCodeMap calculateIncentiveFee(@RequestBody @Valid CalculateIncentiveFeeForm calculateIncentiveFeeForm) {
        String res = this.awardRuleService.calculateIncentiveFee(calculateIncentiveFeeForm.getDriverId(), calculateIncentiveFeeForm.getAcceptTime(), "Chinese");
        return ResponseCodeMap.ok().put("result", res);
    }
}
