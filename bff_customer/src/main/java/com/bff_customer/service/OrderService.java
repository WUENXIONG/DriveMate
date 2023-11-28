package com.bff_customer.service;

import com.bff_customer.controller.form.*;

import java.util.HashMap;

public interface OrderService {
    public HashMap createNewOrder(CreateNewOrderForm form);

    public Integer searchOrderStatus(SearchOrderStatusForm form);

    public String deleteUnAcceptOrder(DeleteUnAcceptOrderForm form);

    public HashMap searchOrderForMoveById(SearchOrderForMoveByIdForm form);

    public HashMap hasCustomerCurrentOrder(HasCustomerCurrentOrderForm form);

    public boolean confirmArriveStartPlace(ConfirmArriveStartPlaceForm form);
}
