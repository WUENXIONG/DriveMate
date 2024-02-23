package com.order.feign;

import com.common.util.ResponseCodeMap;
import com.order.controller.form.TransferForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "order")
public interface DriverServiceAPI {

    @PostMapping("/wallet/income/transfer")
    public ResponseCodeMap transfer(TransferForm form);

}
