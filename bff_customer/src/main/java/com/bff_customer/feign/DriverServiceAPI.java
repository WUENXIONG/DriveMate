package com.bff_customer.feign;

import com.bff_customer.controller.form.SearchDriverBriefInfoForm;
import com.bff_customer.controller.form.SearchDriverOpenIdForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "driver")
public interface DriverServiceAPI {

    @PostMapping("/driver/searchDriverBriefInfo")
    public ResponseCodeMap searchDriverBriefInfo(SearchDriverBriefInfoForm form);

    @PostMapping("/driver/searchDriverOpenId")
    public ResponseCodeMap searchDriverOpenId(SearchDriverOpenIdForm form);


}
