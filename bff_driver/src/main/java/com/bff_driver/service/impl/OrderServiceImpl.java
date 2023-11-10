package com.bff_driver.service.impl;

import cn.hutool.core.map.MapUtil;
import com.bff_driver.controller.form.AcceptNewOrderForm;
import com.bff_driver.controller.form.SearchCustomerInfoInOrderForm;
import com.bff_driver.controller.form.SearchDriverExecuteOrderForm;
import com.bff_driver.feign.CustomerServiceAPI;
import com.bff_driver.feign.OrderServiceAPI;
import com.bff_driver.service.OrderService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.util.ResponseCodeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderServiceAPI orderServiceAPI;

    @Resource
    private CustomerServiceAPI customerServiceAPI;

    @Override
    @LcnTransaction
    @Transactional
    public String acceptNewOrder(AcceptNewOrderForm form) {
        ResponseCodeMap r = orderServiceAPI.acceptNewOrder(form);
        String result = MapUtil.getStr(r, "result");
        return result;
    }

    @Override
    public HashMap searchDriverExecuteOrder(SearchDriverExecuteOrderForm form) {
        //查询订单信息
        ResponseCodeMap r = orderServiceAPI.searchDriverExecuteOrder(form);
        HashMap orderMap = (HashMap) r.get("result");

        //查询代驾客户信息
        long customerId = MapUtil.getLong(orderMap, "customerId");
        SearchCustomerInfoInOrderForm infoInOrderForm = new SearchCustomerInfoInOrderForm();
        infoInOrderForm.setCustomerId(customerId);
        r = customerServiceAPI.searchCustomerInfoInOrder(infoInOrderForm);
        HashMap cstMap = (HashMap) r.get("result");

        HashMap map = new HashMap();
        map.putAll(orderMap);
        map.putAll(cstMap);
        return map;
    }


}
