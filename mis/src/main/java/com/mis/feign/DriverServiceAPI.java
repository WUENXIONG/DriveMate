package com.mis.feign;

import com.common.util.ResponseCodeMap;
import com.mis.controller.form.SearchDriverBriefInfoForm;
import com.mis.controller.form.SearchDriverByPageForm;
import com.mis.controller.form.SearchDriverRealSummaryForm;
import com.mis.controller.form.UpdateDriverRealAuthForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "driver")
public interface DriverServiceAPI {

    @PostMapping("/driver/searchDriverByPage")
    public ResponseCodeMap searchDriverByPage(SearchDriverByPageForm form);

    @PostMapping("/driver/searchDriverRealSummary")
    public ResponseCodeMap searchDriverRealSummary(SearchDriverRealSummaryForm form);

    @PostMapping("/driver/updateDriverRealAuth")
    public ResponseCodeMap updateDriverRealAuth(UpdateDriverRealAuthForm form);

    @PostMapping("/driver/searchDriverBriefInfo")
    public ResponseCodeMap searchDriverBriefInfo(SearchDriverBriefInfoForm form);


}

