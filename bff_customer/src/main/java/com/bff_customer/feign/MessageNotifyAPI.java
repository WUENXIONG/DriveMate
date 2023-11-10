package com.bff_customer.feign;

import com.bff_customer.controller.form.SendNewOrderMessageForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "message-notification")
public interface MessageNotifyAPI {
    @PostMapping("/message/order/new/sendNewOrderMessageAsync")
    public ResponseCodeMap sendNewOrderMessageAsync(SendNewOrderMessageForm form);

}
