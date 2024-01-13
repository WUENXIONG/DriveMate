package com.mis.feign;

import com.common.util.ResponseCodeMap;
import com.mis.controller.form.SearchOrderGpsForm;
import com.mis.controller.form.SearchOrderLastGpsForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "nebula")
public interface NebulaServiceAPI {

    @PostMapping("/order/gps/searchOrderGps")
    public ResponseCodeMap searchOrderGps(SearchOrderGpsForm form);

    @PostMapping("/order/gps/searchOrderLastGps")
    public ResponseCodeMap searchOrderLastGps(SearchOrderLastGpsForm form);


}
