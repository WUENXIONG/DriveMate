package com.bff_driver.service.impl;

import cn.hutool.core.map.MapUtil;
import com.bff_driver.controller.form.*;
import com.bff_driver.feign.CustomerServiceAPI;
import com.bff_driver.feign.NebulaServiceAPI;
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

    @Resource
    private NebulaServiceAPI nebulaServiceAPI;

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

    @Override
    public HashMap searchDriverCurrentOrder(SearchDriverCurrentOrderForm form) {
        ResponseCodeMap r = orderServiceAPI.searchDriverCurrentOrder(form);
        HashMap orderMap = (HashMap) r.get("result");

        if (MapUtil.isNotEmpty(orderMap)) {
            HashMap map = new HashMap();
            //查询代驾客户信息
            long customerId = MapUtil.getLong(orderMap, "customerId");
            SearchCustomerInfoInOrderForm infoInOrderForm = new SearchCustomerInfoInOrderForm();
            infoInOrderForm.setCustomerId(customerId);
            r = customerServiceAPI.searchCustomerInfoInOrder(infoInOrderForm);
            HashMap cstMap = (HashMap) r.get("result");
            map.putAll(orderMap);
            map.putAll(cstMap);
            return map;
        } else {
            return null;
        }
    }

    @Override
    public HashMap searchOrderForMoveById(SearchOrderForMoveByIdForm form) {
        ResponseCodeMap r = orderServiceAPI.searchOrderForMoveById(form);
        HashMap map = (HashMap) r.get("result");
        return map;
    }

    @Override
    @Transactional
    @LcnTransaction
    public int arriveStartPlace(ArriveStartPlaceForm form) {
        ResponseCodeMap r = orderServiceAPI.arriveStartPlace(form);
        int rows = MapUtil.getInt(r, "rows");
        if (rows == 1) {
            //TODO 发送通知消息
        }
        return rows;
    }

    @Override
    @Transactional
    @LcnTransaction
    public int startDriving(StartDrivingForm form) {
        ResponseCodeMap r = orderServiceAPI.startDriving(form);
        int rows = MapUtil.getInt(r, "rows");

        if(rows == 1){
            InsertOrderMonitoringForm monitoringForm = new InsertOrderMonitoringForm();
            monitoringForm.setOrderId(form.getOrderId());
            nebulaServiceAPI.insertOrderMonitoring(monitoringForm);
            //TODO 发送通知消息
        }

        return rows;
    }

    @Override
    @Transactional
    @LcnTransaction
    public int updateOrderStatus(UpdateOrderStatusForm form) {
        ResponseCodeMap r = orderServiceAPI.updateOrderStatus(form);
        int rows = MapUtil.getInt(r, "rows");
        //TODO 判断订单的状态，然后实现后续业务
        return rows;
    }


}
