package com.bff_customer.service.impl;

import cn.hutool.core.map.MapUtil;
import com.bff_customer.controller.form.*;
import com.bff_customer.feign.CustomerServiceAPI;
import com.bff_customer.service.CustomerCarService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.util.ResponseCodeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CustomerCarServiceImpl implements CustomerCarService {
    @Resource
    private CustomerServiceAPI cstServiceApi;

    @Override
    @Transactional
    @LcnTransaction
    public void insertCustomerCar(InsertCustomerCarForm form) {
        cstServiceApi.insertCustomerCar(form);
    }

    @Override
    public ArrayList<HashMap> searchCustomerCarList(SearchCustomerCarListForm form) {
        ResponseCodeMap r = cstServiceApi.searchCustomerCarList(form);
        ArrayList<HashMap> list = (ArrayList<HashMap>) r.get("result");
        return list;
    }

    @Override
    @Transactional
    @LcnTransaction
    public int deleteCustomerCarById(DeleteCustomerCarByIdForm form) {
        ResponseCodeMap r = cstServiceApi.deleteCustomerCarById(form);
        int rows = MapUtil.getInt(r, "rows");
        return rows;
    }
}

