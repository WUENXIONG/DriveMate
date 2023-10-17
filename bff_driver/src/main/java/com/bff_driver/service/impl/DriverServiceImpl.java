package com.bff_driver.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import com.bff_driver.controller.form.*;
import com.bff_driver.feign.DriverServiceAPI;
import com.bff_driver.feign.OrderServiceAPI;
import com.bff_driver.service.DriverService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.util.CosUtil;
import com.common.util.ResponseCodeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class DriverServiceImpl implements DriverService {

    @Resource
    private DriverServiceAPI driverServiceAPI;

    @Resource
    private OrderServiceAPI orderServiceAPI;

    @Resource
    private CosUtil cosUtil;



    @Override
    @Transactional
    @LcnTransaction
    public long registerNewDriver(RegisterNewDriverForm form) {

        ResponseCodeMap r = driverServiceAPI.registerNewDriver(form);
        long userId = Convert.toLong(r.get("userId"));
        return userId;

    }

    @Override
    public int updateDriverAuth(UpdateDriverAuthForm form) {
        ResponseCodeMap r = driverServiceAPI.updateDriverAuth(form);
        int rows = Convert.toInt(r.get("rows"));
        return rows;
    }

    @Override
    @Transactional
    @LcnTransaction
    public String createDriverFaceModel(CreateDriverFaceModelForm form){
        ResponseCodeMap r = driverServiceAPI.createDiverFaceModel(form);
        String result = MapUtil.getStr(r,"result");
        return result;
    }

    @Override
    public HashMap login(LoginForm form) {
        ResponseCodeMap r = driverServiceAPI.login(form);
        HashMap map = (HashMap) r.get("result");
        return map;
    }

    @Override
    public HashMap searchDriverBaseInfo(SearchDriverBaseInfoForm form) {
        ResponseCodeMap r = driverServiceAPI.searchDriverBaseInfo(form);
        HashMap map = (HashMap) r.get("result");
        return map;
    }

    @Override
    public HashMap searchWorkbenchData(long driverId) {

        SearchDriverTodayBusinessDataForm form_1 = new SearchDriverTodayBusinessDataForm();
        form_1.setDriverId(driverId);
        ResponseCodeMap r = orderServiceAPI.searchDriverTodayBusinessData(form_1);
        HashMap business = (HashMap) r.get("result");

        SearchDriverSettingsForm form_2 = new SearchDriverSettingsForm();
        form_2.setDriverId(driverId);
        r = driverServiceAPI.searchDriverSettings(form_2);
        HashMap settings = (HashMap) r.get("result");
        HashMap result = new HashMap() {{
            put("business", business);
            put("settings", settings);
        }};
        return result;
    }

    @Override
    public HashMap searchDriverAuth(SearchDriverAuthForm form) {
        ResponseCodeMap r = driverServiceAPI.searchDriverAuth(form);
        HashMap map = (HashMap) r.get("result");
        //获取私有读写文件的临时URL地址
        String idcardFront = MapUtil.getStr(map, "idcardFront");
        String idcardBack = MapUtil.getStr(map, "idcardBack");
        String idcardHolding = MapUtil.getStr(map, "idcardHolding");
        String drcardFront = MapUtil.getStr(map, "drcardFront");
        String drcardBack = MapUtil.getStr(map, "drcardBack");
        String drcardHolding = MapUtil.getStr(map, "drcardHolding");
        String idcardFrontUrl = idcardFront.length() > 0 ? cosUtil.getPrivateFileUrl(idcardFront) : "";
        String idcardBackUrl = idcardBack.length() > 0 ? cosUtil.getPrivateFileUrl(idcardBack) : "";
        String idcardHoldingUrl = idcardHolding.length() > 0 ? cosUtil.getPrivateFileUrl(idcardHolding) : "";
        String drcardFrontUrl = drcardFront.length() > 0 ? cosUtil.getPrivateFileUrl(drcardFront) : "";
        String drcardBackUrl = drcardBack.length() > 0 ? cosUtil.getPrivateFileUrl(drcardBack) : "";
        String drcardHoldingUrl = drcardHolding.length() > 0 ? cosUtil.getPrivateFileUrl(drcardHolding) : "";
        map.put("idcardFrontUrl", idcardFrontUrl);
        map.put("idcardBackUrl", idcardBackUrl);
        map.put("idcardHoldingUrl", idcardHoldingUrl);
        map.put("drcardFrontUrl", drcardFrontUrl);
        map.put("drcardBackUrl", drcardBackUrl);
        map.put("drcardHoldingUrl", drcardHoldingUrl);
        return map;
    }


}
