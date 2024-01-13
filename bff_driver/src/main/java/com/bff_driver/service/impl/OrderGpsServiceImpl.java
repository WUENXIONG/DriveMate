package com.bff_driver.service.impl;

import cn.hutool.core.map.MapUtil;
import com.bff_driver.controller.form.InsertOrderGpsForm;
import com.bff_driver.feign.NebulaServiceAPI;
import com.bff_driver.service.OrderGpsService;
import com.common.util.ResponseCodeMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderGpsServiceImpl implements OrderGpsService {
    @Resource
    private NebulaServiceAPI nebulaServiceApi;

    @Override
    public int insertOrderGps(InsertOrderGpsForm form) {
        ResponseCodeMap r = nebulaServiceApi.insertOrderGps(form);
        int rows = MapUtil.getInt(r, "rows");
        return rows;
    }
}

