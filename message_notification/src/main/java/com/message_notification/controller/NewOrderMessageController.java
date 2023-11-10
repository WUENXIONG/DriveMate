package com.message_notification.controller;

import com.common.util.ResponseCodeMap;
import com.message_notification.controller.form.ClearNewOrderQueueForm;
import com.message_notification.controller.form.DeleteNewOrderQueueForm;
import com.message_notification.controller.form.ReceiveNewOrderMessageForm;
import com.message_notification.controller.form.SendNewOrderMessageForm;
import com.message_notification.entity.NewOrderMessage;
import com.message_notification.task.NewOrderMassageTask;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/message/order/new")
@Tag(name = "NewOrderMessageController", description = "新订单消息Web接口")
public class NewOrderMessageController {
    @Resource
    private NewOrderMassageTask task;

    @PostMapping("/sendNewOrderMessage")
    @Operation(summary = "同步发送新订单消息")
    public ResponseCodeMap sendNewOrderMessage(@RequestBody @Valid SendNewOrderMessageForm form) {
        ArrayList<NewOrderMessage> list = new ArrayList<>();
        String[] driversContent = form.getDriversContent();
        for (String one : driversContent) {
            String[] temp = one.split("#");
            String userId = temp[0];
            String distance = temp[1];
            NewOrderMessage message = new NewOrderMessage();
            message.setUserId(userId);
            message.setOrderId(form.getOrderId().toString());
            message.setFrom(form.getFrom());
            message.setTo(form.getTo());
            message.setMileage(form.getMileage());
            message.setMinute(form.getMinute().toString());
            message.setDistance(distance);
            message.setExpectsFee(form.getExpectsFee());
            message.setFavourFee(form.getFavourFee());
            list.add(message);
        }

        task.sendNewOrderMessage(list);
        return ResponseCodeMap.ok();
    }

    @PostMapping("/sendNewOrderMessageAsync")
    @Operation(summary = "异步发送新订单消息")
    public ResponseCodeMap sendNewOrderMessageAsync(@RequestBody @Valid SendNewOrderMessageForm form) {
        ArrayList<NewOrderMessage> list = new ArrayList<>();
        String[] driversContent = form.getDriversContent();
        for (String one : driversContent) {
            String[] temp = one.split("#");
            String userId = temp[0];
            String distance = temp[1];
            NewOrderMessage message = new NewOrderMessage();
            message.setUserId(userId);
            message.setOrderId(form.getOrderId().toString());
            message.setFrom(form.getFrom());
            message.setTo(form.getTo());
            message.setMileage(form.getMileage());
            message.setMinute(form.getMinute().toString());
            message.setDistance(distance);
            message.setExpectsFee(form.getExpectsFee());
            message.setFavourFee(form.getFavourFee());
            list.add(message);
        }
        task.sendNewOrderMessageAsync(list);
        return ResponseCodeMap.ok();
    }

    @PostMapping("/receiveNewOrderMessage")
    @Operation(summary = "同步接收新订单消息")
    public ResponseCodeMap receiveNewOrderMessage(@RequestBody @Valid ReceiveNewOrderMessageForm form) {
        List<NewOrderMessage> list = task.receiveNewOrderMessage(form.getUserId());
        return ResponseCodeMap.ok().put("result", list);
    }

    @PostMapping("/deleteNewOrderQueue")
    @Operation(summary = "同步删除新订单消息队列")
    public ResponseCodeMap deleteNewOrderQueue(@RequestBody @Valid DeleteNewOrderQueueForm form) {
        task.deleteNewOrderQueue(form.getUserId());
        return ResponseCodeMap.ok();
    }

    @PostMapping("/deleteNewOrderQueueeAsync")
    @Operation(summary = "异步删除新订单消息队列")
    public ResponseCodeMap deleteNewOrderQueueeAsync(@RequestBody @Valid DeleteNewOrderQueueForm form) {
        task.deleteNewOrderQueueAsync(form.getUserId());
        return ResponseCodeMap.ok();
    }

    @PostMapping("/clearNewOrderQueue")
    @Operation(summary = "同步清空新订单消息队列")
    public ResponseCodeMap clearNewOrderQueue(@RequestBody @Valid ClearNewOrderQueueForm form) {
        task.clearNewOrderQueue(form.getUserId());
        return ResponseCodeMap.ok();
    }

    @PostMapping("/clearNewOrderQueueAsync")
    @Operation(summary = "异步清空新订单消息队列")
    public ResponseCodeMap clearNewOrderQueueAsync(@RequestBody @Valid ClearNewOrderQueueForm form) {
        task.clearNewOrderQueueAsync(form.getUserId());
        return ResponseCodeMap.ok();
    }



}
