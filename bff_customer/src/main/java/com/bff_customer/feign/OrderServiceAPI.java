package com.bff_customer.feign;

import com.bff_customer.controller.form.DeleteUnAcceptOrderForm;
import com.bff_customer.controller.form.InsertOrderForm;
import com.bff_customer.controller.form.SearchOrderStatusForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("order")
public interface OrderServiceAPI {
    @PostMapping("/order/insertOrder")
    public ResponseCodeMap insertOrder(InsertOrderForm form);

    @PostMapping("/order/searchOrderStatus")
    public ResponseCodeMap searchOrderStatus(SearchOrderStatusForm form);

    @PostMapping("/order/deleteUnAcceptOrder")
    public ResponseCodeMap deleteUnAcceptOrder(DeleteUnAcceptOrderForm form);


}
