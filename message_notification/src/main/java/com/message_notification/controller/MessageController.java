package com.message_notification.controller;

import com.common.util.ResponseCodeMap;
import com.message_notification.controller.form.*;
import com.message_notification.db.pojo.MessageEntity;
import com.message_notification.service.MessageService;
import com.message_notification.task.MessageTask;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/message")
@Tag(name = "MessageController", description = "消息模块网络接口")
public class MessageController {

    @Resource
    private MessageService messageService;

    @Resource
    private MessageTask messageTask;

    @PostMapping("/searchMessageById")
    @Operation(summary = "根据ID查询消息")
    public ResponseCodeMap searchMessageById(@Valid @RequestBody SearchMessageByIdForm form) {
        HashMap map = messageService.searchMessageById(form.getId());
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/updateUnreadMessage")
    @Operation(summary = "未读消息更新成已读消息")
    public ResponseCodeMap updateUnreadMessage(@Valid @RequestBody UpdateUnreadMessageForm form) {
        long rows = messageService.updateUnreadMessage(form.getId());
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/deleteMessageRefById")
    @Operation(summary = "删除消息")
    public ResponseCodeMap deleteMessageRefById(@Valid @RequestBody DeleteMessageRefByIdForm form) {
        long rows = messageService.deleteMessageRefById(form.getId());
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/refreshMessage")
    @Operation(summary = "刷新用户消息")
    public ResponseCodeMap refreshMessage(@Valid @RequestBody RefreshMessageForm form) {
        messageTask.receive(form.getIdentity(), form.getUserId());
        long lastRows = messageService.searchLastCount(form.getUserId(), form.getIdentity());
        long unreadRows = messageService.searchUnreadCount(form.getUserId(), form.getIdentity());
        HashMap map = new HashMap(){{
            put("lastRows", lastRows + "");
            put("unreadRows", unreadRows + "");
        }};
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/sendPrivateMessage")
    @Operation(summary = "同步发送私有消息")
    public ResponseCodeMap sendPrivateMessage(@RequestBody @Valid SendPrivateMessageForm form) {
        MessageEntity entity = new MessageEntity();
        entity.setSenderId(form.getSenderId());
        entity.setSenderIdentity(form.getSenderIdentity());
        entity.setSenderPhoto(form.getSenderPhoto());
        entity.setSenderName(form.getSenderName());
        entity.setMsg(form.getMsg());
        entity.setSendTime(new Date());
        messageTask.sendPrivateMessage(form.getReceiverIdentity(), form.getReceiverId(), form.getTtl(), entity);
        return ResponseCodeMap.ok();
    }

    @PostMapping("/sendPrivateMessageSync")
    @Operation(summary = "异步发送私有消息")
    public ResponseCodeMap sendPrivateMessageSync(@RequestBody @Valid SendPrivateMessageForm form) {
        MessageEntity entity = new MessageEntity();
        entity.setSenderId(form.getSenderId());
        entity.setSenderIdentity(form.getSenderIdentity());
        entity.setSenderPhoto(form.getSenderPhoto());
        entity.setSenderName(form.getSenderName());
        entity.setMsg(form.getMsg());
        entity.setSendTime(new Date());
        messageTask.sendPrivateMessageAsync(form.getReceiverIdentity(), form.getReceiverId(), form.getTtl(), entity);
        return ResponseCodeMap.ok();
    }

    @PostMapping("/receiveBillMessage")
    @Operation(summary = "同步接收新订单消息")
    public ResponseCodeMap receiveBillMessage(@RequestBody @Valid ReceiveBillMessageForm form) {
        String msg = messageTask.receiveBillMessage(form.getIdentity(), form.getUserId());
        return ResponseCodeMap.ok().put("result", msg);
    }


}

