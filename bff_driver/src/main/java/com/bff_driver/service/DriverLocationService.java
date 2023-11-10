package com.bff_driver.service;

import com.bff_driver.controller.form.ClearNewOrderQueueForm;
import com.bff_driver.controller.form.RemoveLocationCacheForm;
import com.bff_driver.controller.form.UpdateLocationCacheForm;

public interface DriverLocationService {

    public void updateLocationCache(UpdateLocationCacheForm form);

    public void removeLocationCache(RemoveLocationCacheForm form);


}
