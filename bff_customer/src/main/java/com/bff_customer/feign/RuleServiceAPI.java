package com.bff_customer.feign;

import com.bff_customer.controller.form.EstimateOrderChargeForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "rule")
public interface RuleServiceAPI {
    @PostMapping("/charge/estimateOrderCharge")
    public ResponseCodeMap estimateOrderCharge(EstimateOrderChargeForm form);

}
