package com.bff_driver.feign;

import com.bff_driver.controller.form.*;
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

    @PostMapping("/order/searchDriverCurrentOrder")
    public ResponseCodeMap searchDriverCurrentOrder(SearchDriverCurrentOrderForm form);

    @PostMapping("/order/searchOrderForMoveById")
    public ResponseCodeMap searchOrderForMoveById(SearchOrderForMoveByIdForm form);

    @PostMapping("/order/arriveStartPlace")
    public ResponseCodeMap arriveStartPlace(ArriveStartPlaceForm form);

    @PostMapping("/order/startDriving")
    public ResponseCodeMap startDriving(StartDrivingForm form);

    @PostMapping("/order/updateOrderStatus")
    public ResponseCodeMap updateOrderStatus(UpdateOrderStatusForm form);

    @PostMapping("/order/validDriverOwnOrder")
    public ResponseCodeMap validDriverOwnOrder(ValidDriverOwnOrderForm form);

    @PostMapping("/order/searchSettlementNeedData")
    public ResponseCodeMap searchSettlementNeedData(SearchSettlementNeedDataForm form);

    @PostMapping("/bill/updateBillFee")
    public ResponseCodeMap updateBillFee(UpdateBillFeeForm form);

    @PostMapping("/bill/searchReviewDriverOrderBill")
    public ResponseCodeMap searchReviewDriverOrderBill(SearchReviewDriverOrderBillForm form);


    @PostMapping("/order/searchOrderStatus")
    public ResponseCodeMap searchOrderStatus(SearchOrderStatusForm form);

    @PostMapping("/order/updateOrderAboutPayment")
    public ResponseCodeMap updateOrderAboutPayment(UpdateOrderAboutPaymentForm form);


}

