package com.customer.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.customer.db.dao.CustomerCarDao;
import com.customer.db.pojo.CustomerCarEntity;
import com.customer.service.CustomerCarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CustomerCarServiceImpl implements CustomerCarService {
    @Resource
    private CustomerCarDao customerCarDao;

    @Override
    @Transactional
    @LcnTransaction
    public void insertCustomerCar(CustomerCarEntity entity) {
        customerCarDao.insert(entity);
    }

    @Override
    public ArrayList<HashMap> searchCustomerCarList(long customerId) {
        ArrayList list = customerCarDao.searchCustomerCarList(customerId);
        return list;
    }

    @Override
    @Transactional
    @LcnTransaction
    public int deleteCustomerCarById(long id) {
        int rows = customerCarDao.deleteCustomerCarById(id);
        return rows;
    }
}

