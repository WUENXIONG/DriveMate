package com.bff_customer.service;

import com.bff_customer.controller.form.DeleteCustomerCarByIdForm;
import com.bff_customer.controller.form.InsertCustomerCarForm;
import com.bff_customer.controller.form.SearchCustomerCarListForm;

import java.util.ArrayList;
import java.util.HashMap;

public interface CustomerCarService {
    public void insertCustomerCar(InsertCustomerCarForm form);

    public ArrayList<HashMap> searchCustomerCarList(SearchCustomerCarListForm form);

    public int deleteCustomerCarById(DeleteCustomerCarByIdForm form);

}
