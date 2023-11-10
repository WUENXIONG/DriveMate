package com.bff_customer.service;

import com.bff_customer.controller.form.LoginForm;
import com.bff_customer.controller.form.RegisterNewCustomerForm;

public interface CustomerService {
    public long registerNewCustomer(RegisterNewCustomerForm form);

    public Long login(LoginForm form);

}
