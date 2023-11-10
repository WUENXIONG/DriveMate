package com.bff_driver.feign;

import com.bff_driver.controller.form.ClearNewOrderQueueForm;
import com.bff_driver.controller.form.ReceiveNewOrderMessageForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "message-notification")
public interface MessageNotifyAPI {
    @PostMapping("/message/order/new/clearNewOrderQueue")
    public ResponseCodeMap clearNewOrderQueue(ClearNewOrderQueueForm form);

    @PostMapping("/message/order/new/receiveNewOrderMessage")
    public ResponseCodeMap receiveNewOrderMessage(ReceiveNewOrderMessageForm form);

}
