package com.mis.feign;

import com.common.util.ResponseCodeMap;
import com.mis.controller.form.CalculateDriveLineForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "map")
public interface MapServiceAPI {

    @PostMapping("/map/calculateDriveLine")
    public ResponseCodeMap calculateDriveLine(CalculateDriveLineForm form);


}
