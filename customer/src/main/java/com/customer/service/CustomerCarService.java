package com.customer.service;

import com.customer.db.pojo.CustomerCarEntity;

import java.util.ArrayList;
import java.util.HashMap;

public interface CustomerCarService {
    public void insertCustomerCar(CustomerCarEntity entity);

    public ArrayList<HashMap> searchCustomerCarList(long customerId);

    public int deleteCustomerCarById(long id);

}
