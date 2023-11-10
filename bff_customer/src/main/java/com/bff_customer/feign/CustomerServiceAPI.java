package com.bff_customer.feign;

import com.bff_customer.controller.form.*;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "customer")
public interface CustomerServiceAPI {

    @PostMapping("/customer/registerNewCustomer")
    public ResponseCodeMap registerNewCustomer(RegisterNewCustomerForm form);

    @PostMapping("/customer/login")
    public ResponseCodeMap login(LoginForm form);

    @PostMapping("/customer/car/insertCustomerCar")
    public ResponseCodeMap insertCustomerCar(InsertCustomerCarForm form);

    @PostMapping("/customer/car/searchCustomerCarList")
    public ResponseCodeMap searchCustomerCarList(SearchCustomerCarListForm form);

    @PostMapping("/customer/car/deleteCustomerCarById")
    public ResponseCodeMap deleteCustomerCarById(DeleteCustomerCarByIdForm form);


}
