package com.bff_customer.service.impl;

import cn.hutool.core.map.MapUtil;
import com.bff_customer.controller.form.ReceiveBillMessageForm;
import com.bff_customer.feign.MessageNotifyAPI;
import com.bff_customer.service.MessageService;
import com.common.util.ResponseCodeMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageNotifyAPI messageNotifyAPI;

    @Override
    public String receiveBillMessage(ReceiveBillMessageForm form) {
        ResponseCodeMap r = messageNotifyAPI.receiveBillMessage(form);
        String msg = MapUtil.getStr(r, "result");
        return msg;
    }


}
