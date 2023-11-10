package com.bff_customer.service;

import com.bff_customer.controller.form.CreateNewOrderForm;
import com.bff_customer.controller.form.DeleteUnAcceptOrderForm;
import com.bff_customer.controller.form.SearchOrderStatusForm;

import java.util.HashMap;

public interface OrderService {
    public HashMap createNewOrder(CreateNewOrderForm form);

    public Integer searchOrderStatus(SearchOrderStatusForm form);

    public String deleteUnAcceptOrder(DeleteUnAcceptOrderForm form);

}
