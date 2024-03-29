package com.bff_driver.service;

import com.bff_driver.controller.form.*;

import java.util.HashMap;

public interface OrderService {

    public String acceptNewOrder(AcceptNewOrderForm form);

    public HashMap searchDriverExecuteOrder(SearchDriverExecuteOrderForm form);

    public HashMap searchDriverCurrentOrder(SearchDriverCurrentOrderForm form);

    public HashMap searchOrderForMoveById(SearchOrderForMoveByIdForm form);

    public int arriveStartPlace(ArriveStartPlaceForm form);

    public int startDriving(StartDrivingForm form);

    public int updateOrderStatus(UpdateOrderStatusForm form);

    public int updateOrderBill(UpdateBillFeeForm form);

    public HashMap searchReviewDriverOrderBill(SearchReviewDriverOrderBillForm form);

    public Integer searchOrderStatus(SearchOrderStatusForm form);

    public String updateOrderAboutPayment(long driverId, UpdateOrderAboutPaymentForm form);

}
