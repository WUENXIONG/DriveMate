package com.customer.service;

import java.util.HashMap;
import java.util.Map;

public interface CustomerService {

    public String registerNewCustomer(Map param);

    public String login(String code);

    public HashMap searchCustomerInfoInOrder(long customerId);

    public HashMap searchCustomerBriefInfo(long customerId);
}
