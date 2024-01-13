package com.mis.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.common.exception.DriveMateException;
import com.common.util.DataPaging;
import com.common.util.ResponseCodeMap;
import com.mis.controller.form.*;
import com.mis.feign.*;
import com.mis.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderServiceAPI orderServiceAPI;

    @Resource
    CustomerServiceAPI customerServiceAPI;

    @Resource
    NebulaServiceAPI nebulaServiceAPI;

    @Resource
    DriverServiceAPI driverServiceAPI;

    @Resource
    RuleServiceAPI ruleServiceAPI;

    @Resource
    MapServiceAPI mapServiceAPI;

    @Override
    public DataPaging searchOrderByPage(SearchOrderByPageForm form) {
        ResponseCodeMap r = orderServiceAPI.searchOrderByPage(form);
        HashMap map = (HashMap) r.get("result");
        DataPaging dataPaging = BeanUtil.toBean(map, DataPaging.class);
        return dataPaging;

    }

    @Override
    public HashMap searchOrderComprehensiveInfo(long orderId) {
        HashMap map = new HashMap();

        //查询订单内容信息
        SearchOrderContentForm form_1 = new SearchOrderContentForm();
        form_1.setOrderId(orderId);
        ResponseCodeMap r = orderServiceAPI.searchOrderContent(form_1);
        if (!r.containsKey("result")) {
            throw new DriveMateException("不存在订单记录");
        }
        HashMap content = (HashMap) r.get("result");
        map.put("content", content);

        //查询客户信息
        long customerId = MapUtil.getLong(content, "customerId");
        SearchCustomerBriefInfoForm form_2 = new SearchCustomerBriefInfoForm();
        form_2.setCustomerId(customerId);
        r = customerServiceAPI.searchCustomerBriefInfo(form_2);
        HashMap customerInfo = (HashMap) r.get("result");
        map.put("customerInfo", customerInfo);

        //查询司机信息
        long driverId = MapUtil.getLong(content, "driverId");
        SearchDriverBriefInfoForm form_3 = new SearchDriverBriefInfoForm();
        form_3.setDriverId(driverId);
        r = driverServiceAPI.searchDriverBriefInfo(form_3);
        HashMap driverInfo = (HashMap) r.get("result");
        map.put("driverInfo", driverInfo);

        //查询消费规则
        if (content.containsKey("chargeRuleId")) {
            long chargeRuleId = MapUtil.getLong(content, "chargeRuleId");
            SearchChargeRuleByIdForm form_4 = new SearchChargeRuleByIdForm();
            form_4.setRuleId(chargeRuleId);
            r = ruleServiceAPI.searchChargeRuleById(form_4);
            HashMap chargeRule = (HashMap) r.get("result");
            map.put("chargeRule", chargeRule);
        }

        //查询取消规则
        if (content.containsKey("cancelRuleId")) {
            long cancelRuleId = MapUtil.getLong(content, "cancelRuleId");
            SearchCancelRuleByIdForm form_5 = new SearchCancelRuleByIdForm();
            form_5.setRuleId(cancelRuleId);
            r = ruleServiceAPI.searchCancelRuleById(form_5);
            HashMap cancelRule = (HashMap) r.get("result");
            map.put("cancelRule", cancelRule);
        }

        //查询分账规则
        if (content.containsKey("profitsharingRuleId")) {
            long profitsharingRuleId = MapUtil.getLong(content, "profitsharingRuleId");
            SearchProfitsharingRuleByIdForm form_6 = new SearchProfitsharingRuleByIdForm();
            form_6.setRuleId(profitsharingRuleId);
            r = ruleServiceAPI.searchProfitsharingRuleById(form_6);
            HashMap profitsharingRule = (HashMap) r.get("result");
            map.put("profitsharingRule", profitsharingRule);
        }


        //查询GPS规划线路
        CalculateDriveLineForm form_7 = new CalculateDriveLineForm();
        HashMap startPlaceLocation = (HashMap) content.get("startPlaceLocation");
        HashMap endPlaceLocation = (HashMap) content.get("endPlaceLocation");
        form_7.setStartPlaceLatitude(MapUtil.getStr(startPlaceLocation, "latitude"));
        form_7.setStartPlaceLongitude(MapUtil.getStr(startPlaceLocation, "longitude"));
        form_7.setEndPlaceLatitude(MapUtil.getStr(endPlaceLocation, "latitude"));
        form_7.setEndPlaceLongitude(MapUtil.getStr(endPlaceLocation, "longitude"));
        r = mapServiceAPI.calculateDriveLine(form_7);
        HashMap driveLine = (HashMap) r.get("result");
        map.put("driveLine", driveLine);

        int status = MapUtil.getInt(content, "status");
        if (status >= 5 && status <= 8) {
            //查询订单GPS定位记录
            SearchOrderGpsForm form_8 = new SearchOrderGpsForm();
            form_8.setOrderId(orderId);
            r = nebulaServiceAPI.searchOrderGps(form_8);
            ArrayList<HashMap> orderGps = (ArrayList<HashMap>) r.get("result");
            map.put("orderGps", orderGps);
        } else if (status == 4) {
            //查询订单中最后的GPS定位
            SearchOrderLastGpsForm form_9 = new SearchOrderLastGpsForm();
            form_9.setOrderId(orderId);
            r = nebulaServiceAPI.searchOrderLastGps(form_9);
            HashMap lastGps = (HashMap) r.get("result");
            map.put("lastGps", lastGps);
        }
        return map;
    }


    @Override
    public HashMap searchOrderLastGps(SearchOrderLastGpsForm form) {
        SearchOrderStatusForm statusForm = new SearchOrderStatusForm();
        statusForm.setOrderId(form.getOrderId());
        ResponseCodeMap r = orderServiceAPI.searchOrderStatus(statusForm);
        if (!r.containsKey("result")) {
            throw new DriveMateException("没有对应的订单记录");
        }
        int status = MapUtil.getInt(r, "result");
        if (status == 4) {
            //查询订单最后的GPS记录
            r = nebulaServiceAPI.searchOrderLastGps(form);
            HashMap lastGps = (HashMap) r.get("result");
            return lastGps;
        } else {
            return null;
        }
    }


    @Override
    public ArrayList<HashMap> searchOrderStartLocationIn30Days() {
        ResponseCodeMap r = orderServiceAPI.searchOrderStartLocationIn30Days();
        ArrayList<HashMap> list = (ArrayList<HashMap>) r.get("result");
        ArrayList<HashMap> result = new ArrayList<>();
        Map<HashMap, Integer> map = CollectionUtil.countMap(list);
        map.forEach((key, value) -> {
            key.replace("latitude", MapUtil.getDouble(key, "latitude"));
            key.replace("longitude", MapUtil.getDouble(key, "longitude"));
            key.put("count", value);
            result.add(key);
        });
        return result;
    }


}
