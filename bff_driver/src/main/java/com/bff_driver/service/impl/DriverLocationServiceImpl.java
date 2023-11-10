package com.bff_driver.service.impl;

import com.bff_driver.controller.form.ClearNewOrderQueueForm;
import com.bff_driver.controller.form.RemoveLocationCacheForm;
import com.bff_driver.controller.form.UpdateLocationCacheForm;
import com.bff_driver.feign.MapServiceAPI;
import com.bff_driver.feign.MessageNotifyAPI;
import com.bff_driver.service.DriverLocationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DriverLocationServiceImpl implements DriverLocationService {

    @Resource
    private MapServiceAPI mapServiceAPI;



    @Override
    public void updateLocationCache(UpdateLocationCacheForm form) {
        mapServiceAPI.updateLocationCache(form);
    }

    @Override
    public void removeLocationCache(RemoveLocationCacheForm form) {
        mapServiceAPI.removeLocationCache(form);
    }


}
