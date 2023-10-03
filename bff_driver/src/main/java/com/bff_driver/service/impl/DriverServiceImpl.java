package com.bff_driver.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import com.bff_driver.controller.form.CreateDriverFaceModelForm;
import com.bff_driver.controller.form.LoginForm;
import com.bff_driver.controller.form.RegisterNewDriverForm;
import com.bff_driver.controller.form.UpdateDriverAuthForm;
import com.bff_driver.feign.DriverServiceAPI;
import com.bff_driver.service.DriverService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.util.ResponseCodeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class DriverServiceImpl implements DriverService {

    @Resource
    private DriverServiceAPI driverServiceAPI;

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

}
