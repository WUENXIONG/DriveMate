package com.bff_driver.feign;

import com.bff_driver.controller.form.SearchDriverTodayBusinessDataForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "order")
public interface OrderServiceAPI {

    @PostMapping("/order/searchDriverTodayBusinessData")
    public ResponseCodeMap searchDriverTodayBusinessData(SearchDriverTodayBusinessDataForm form);
}

