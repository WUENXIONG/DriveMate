package com.bff_driver.feign;

import com.bff_driver.controller.form.SearchCustomerInfoInOrderForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "customer")
public interface CustomerServiceAPI {

    @PostMapping("/customer/searchCustomerInfoInOrder")
    public ResponseCodeMap searchCustomerInfoInOrder(SearchCustomerInfoInOrderForm form);


}
