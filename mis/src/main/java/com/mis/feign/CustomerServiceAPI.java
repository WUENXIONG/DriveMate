package com.mis.feign;

import com.common.util.ResponseCodeMap;
import com.mis.controller.form.SearchCustomerBriefInfoForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "customer")
public interface CustomerServiceAPI {

    @PostMapping("/customer/searchCustomerBriefInfo")
    public ResponseCodeMap searchCustomerBriefInfo(SearchCustomerBriefInfoForm form);


}
