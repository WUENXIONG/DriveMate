package com.bff_customer.feign;

import com.bff_customer.controller.form.EstimateOrderMileageAndMinuteForm;
import com.bff_customer.controller.form.SearchBefittingDriverAboutOrderForm;
import com.bff_customer.controller.form.SearchOrderLocationCacheForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "map")
public interface MapServiceAPI {

    @PostMapping("/map/estimateOrderMileageAndMinute")
    public ResponseCodeMap estimateOrderMileageAndMinute(EstimateOrderMileageAndMinuteForm form);

    @PostMapping("/driver/location/searchBefittingDriverAboutOrder")
    public ResponseCodeMap searchBefittingDriverAboutOrder(SearchBefittingDriverAboutOrderForm form);

    @PostMapping("/driver/location/searchOrderLocationCache")
    public ResponseCodeMap searchOrderLocationCache(SearchOrderLocationCacheForm form);


}
