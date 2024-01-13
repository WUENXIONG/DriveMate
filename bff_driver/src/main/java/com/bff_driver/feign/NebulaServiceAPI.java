package com.bff_driver.feign;

import com.bff_driver.config.MultipartSupportConfig;
import com.bff_driver.controller.form.InsertOrderGpsForm;
import com.bff_driver.controller.form.InsertOrderMonitoringForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "nebula", configuration = MultipartSupportConfig.class)
public interface NebulaServiceAPI {

    @PostMapping(value = "/monitoring/uploadRecordFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseCodeMap uploadRecordFile(@RequestPart(value = "file") MultipartFile file,
                                            @RequestPart("name") String name,
                                            @RequestPart(value = "text", required = false) String text);


    @PostMapping(value = "/monitoring/insertOrderMonitoring")
    public ResponseCodeMap insertOrderMonitoring(InsertOrderMonitoringForm form);

    @PostMapping("/order/gps/insertOrderGps")
    public ResponseCodeMap insertOrderGps(InsertOrderGpsForm form);


}
