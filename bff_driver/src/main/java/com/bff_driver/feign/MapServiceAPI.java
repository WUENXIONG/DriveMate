package com.bff_driver.feign;

import com.bff_driver.controller.form.ClearNewOrderQueueForm;
import com.bff_driver.controller.form.RemoveLocationCacheForm;
import com.bff_driver.controller.form.UpdateLocationCacheForm;
import com.bff_driver.controller.form.UpdateOrderLocationCacheForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "map")
public interface MapServiceAPI {
    @PostMapping("/driver/location/updateLocationCache")
    public ResponseCodeMap updateLocationCache(UpdateLocationCacheForm form);

    @PostMapping("/driver/location/removeLocationCache")
    public ResponseCodeMap removeLocationCache(RemoveLocationCacheForm form);

    @PostMapping("/driver/location/updateOrderLocationCache")
    public ResponseCodeMap updateOrderLocationCache(UpdateOrderLocationCacheForm form);



}
