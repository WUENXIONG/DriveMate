package com.bff_customer.service.impl;

import com.bff_customer.controller.form.SearchOrderLocationCacheForm;
import com.bff_customer.feign.MapServiceAPI;
import com.bff_customer.service.OrderLocationService;
import com.common.util.ResponseCodeMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
@Slf4j
public class OrderLocationServiceImpl implements OrderLocationService{

    @Resource
    private MapServiceAPI mapServiceAPI;

    @Override
    public HashMap searchOrderLocationCache(SearchOrderLocationCacheForm form) {
        ResponseCodeMap r = mapServiceAPI.searchOrderLocationCache(form);
        HashMap map = (HashMap) r.get("result");
        return map;
    }


}
