package com.mis.feign;

import com.common.util.ResponseCodeMap;
import com.mis.controller.form.SearchCancelRuleByIdForm;
import com.mis.controller.form.SearchChargeRuleByIdForm;
import com.mis.controller.form.SearchProfitsharingRuleByIdForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "rule")
public interface RuleServiceAPI {

    @PostMapping("/charge/searchChargeRuleById")
    public ResponseCodeMap searchChargeRuleById(SearchChargeRuleByIdForm form);

    @PostMapping("/cancel/searchCancelRuleById")
    public ResponseCodeMap searchCancelRuleById(SearchCancelRuleByIdForm form);

    @PostMapping("/profitsharing/searchProfitsharingRuleById")
    public ResponseCodeMap searchProfitsharingRuleById(SearchProfitsharingRuleByIdForm form);


}
