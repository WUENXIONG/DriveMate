package com.bff_driver.service;

import com.bff_driver.controller.form.AcceptNewOrderForm;
import com.bff_driver.controller.form.SearchDriverExecuteOrderForm;

import java.util.HashMap;

public interface OrderService {

    public String acceptNewOrder(AcceptNewOrderForm form);

    public HashMap searchDriverExecuteOrder(SearchDriverExecuteOrderForm form);

}
