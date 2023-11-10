package com.customer.db.dao;


import com.customer.db.pojo.CustomerCarEntity;

import java.util.ArrayList;
import java.util.HashMap;

public interface CustomerCarDao {
    public int insert(CustomerCarEntity entity);

    public ArrayList<HashMap> searchCustomerCarList(long customerId);

    public int deleteCustomerCarById(long id);

}




