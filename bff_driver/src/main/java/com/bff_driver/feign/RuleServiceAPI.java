package com.bff_driver.feign;

import com.bff_driver.controller.form.CalculateIncentiveFeeForm;
import com.bff_driver.controller.form.CalculateOrderChargeForm;
import com.bff_driver.controller.form.CalculateProfitsharingForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "rule")
public interface RuleServiceAPI {

    @PostMapping("/charge/calculateOrderCharge")
    public ResponseCodeMap calculateOrderCharge(CalculateOrderChargeForm form);

    @PostMapping("/award/calculateIncentiveFee")
    public ResponseCodeMap calculateIncentiveFee(CalculateIncentiveFeeForm form);

    @PostMapping("/profitsharing/calculateProfitsharing")
    public ResponseCodeMap calculateProfitsharing(CalculateProfitsharingForm form);


}
