package com.bff_customer.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.bff_customer.controller.form.LoginForm;
import com.bff_customer.controller.form.RegisterNewCustomerForm;
import com.bff_customer.feign.CustomerServiceAPI;
import com.bff_customer.service.CustomerService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.util.ResponseCodeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerServiceAPI customerServiceAPI;

    @Override
    @Transactional
    @LcnTransaction
    public long registerNewCustomer(RegisterNewCustomerForm form) {
        ResponseCodeMap r = customerServiceAPI.registerNewCustomer(form);
        long userId = Convert.toLong(r.get("userId"));
        return userId;
    }

    @Override
    public Long login(LoginForm form) {
        ResponseCodeMap r = customerServiceAPI.login(form);
        String userId = MapUtil.getStr(r, "userId");
        if (!StrUtil.isBlank(userId)) {
            return Convert.toLong(userId);
        }
        return null;
    }


}

