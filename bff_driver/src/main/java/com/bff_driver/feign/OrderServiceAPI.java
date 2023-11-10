package com.bff_driver.feign;

import com.bff_driver.controller.form.AcceptNewOrderForm;
import com.bff_driver.controller.form.SearchDriverExecuteOrderForm;
import com.bff_driver.controller.form.SearchDriverTodayBusinessDataForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "order")
public interface OrderServiceAPI {

    @PostMapping("/order/searchDriverTodayBusinessData")
    public ResponseCodeMap searchDriverTodayBusinessData(SearchDriverTodayBusinessDataForm form);

    @PostMapping("/order/acceptNewOrder")
    public ResponseCodeMap acceptNewOrder(AcceptNewOrderForm form);

    @PostMapping("/order/searchDriverExecuteOrder")
    public ResponseCodeMap searchDriverExecuteOrder(SearchDriverExecuteOrderForm form);


}

