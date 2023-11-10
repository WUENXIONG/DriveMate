package com.bff_driver.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.bff_driver.controller.form.ReceiveNewOrderMessageForm;
import com.bff_driver.service.NewOrderMessageService;
import com.common.util.ResponseCodeMap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping("/message/order/new")
@Tag(name = "NewOrderMessageController", description = "新订单消息Web接口")
public class NewOrderMessageController {
    @Resource
    private NewOrderMessageService newOrderMessageService;

    @PostMapping("/receiveNewOrderMessage")
    @Operation(summary = "同步接收新订单消息")
    @SaCheckLogin
    public ResponseCodeMap receiveNewOrderMessage() {
        long driverId = StpUtil.getLoginIdAsLong();
        ReceiveNewOrderMessageForm form = new ReceiveNewOrderMessageForm();
        form.setUserId(driverId);
        ArrayList list = newOrderMessageService.receiveNewOrderMessage(form);
        return ResponseCodeMap.ok().put("result", list);
    }
}
