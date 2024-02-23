package com.bff_driver.feign;

import com.bff_driver.controller.form.ClearNewOrderQueueForm;
import com.bff_driver.controller.form.ReceiveNewOrderMessageForm;
import com.bff_driver.controller.form.SendPrivateMessageForm;
import com.common.util.ResponseCodeMap;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "message-notification")
public interface MessageNotifyAPI {
    @PostMapping("/message/order/new/clearNewOrderQueue")
    public ResponseCodeMap clearNewOrderQueue(ClearNewOrderQueueForm form);

    @PostMapping("/message/order/new/receiveNewOrderMessage")
    public ResponseCodeMap receiveNewOrderMessage(ReceiveNewOrderMessageForm form);

    @PostMapping("/message/sendPrivateMessage")
    @Operation(summary = "同步发送私有消息")
    public ResponseCodeMap sendPrivateMessage(SendPrivateMessageForm form);

    @PostMapping("/message/sendPrivateMessageSync")
    @Operation(summary = "异步发送私有消息")
    public ResponseCodeMap sendPrivateMessageSync(SendPrivateMessageForm form);

}
