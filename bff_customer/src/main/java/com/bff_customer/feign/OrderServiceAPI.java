package com.bff_customer.feign;

import com.bff_customer.controller.form.*;
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

    @PostMapping("/order/searchOrderForMoveById")
    public ResponseCodeMap searchOrderForMoveById(SearchOrderForMoveByIdForm form);

    @PostMapping("/order/hasCustomerCurrentOrder")
    public ResponseCodeMap hasCustomerCurrentOrder(HasCustomerCurrentOrderForm form);

    @PostMapping("/order/confirmArriveStartPlace")
    public ResponseCodeMap confirmArriveStartPlace(ConfirmArriveStartPlaceForm form);


}
