package com.mis.feign;

import com.common.util.ResponseCodeMap;
import com.mis.controller.form.SearchOrderByPageForm;
import com.mis.controller.form.SearchOrderContentForm;
import com.mis.controller.form.SearchOrderStatusForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "order")
public interface OrderServiceAPI {

    @PostMapping("/order/searchOrderByPage")
    public ResponseCodeMap searchOrderByPage(SearchOrderByPageForm form);

    @PostMapping("/order/searchOrderContent")
    public ResponseCodeMap searchOrderContent(SearchOrderContentForm form);

    @PostMapping("/order/searchOrderStatus")
    public ResponseCodeMap searchOrderStatus(SearchOrderStatusForm form);


    @PostMapping("/order/searchOrderStartLocationIn30Days")
    public ResponseCodeMap searchOrderStartLocationIn30Days();


}
