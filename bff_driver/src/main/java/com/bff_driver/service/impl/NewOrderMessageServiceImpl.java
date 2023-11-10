package com.bff_driver.service.impl;

import com.bff_driver.controller.form.ClearNewOrderQueueForm;
import com.bff_driver.controller.form.ReceiveNewOrderMessageForm;
import com.bff_driver.feign.MessageNotifyAPI;
import com.bff_driver.service.NewOrderMessageService;
import com.common.util.ResponseCodeMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class NewOrderMessageServiceImpl implements NewOrderMessageService {

    @Resource
    private MessageNotifyAPI messageNotifyAPI;
    @Override
    public void clearNewOrderQueue(ClearNewOrderQueueForm form) {
        messageNotifyAPI.clearNewOrderQueue(form);
    }

    @Override
    public ArrayList receiveNewOrderMessage(ReceiveNewOrderMessageForm form) {
        ResponseCodeMap r = messageNotifyAPI.receiveNewOrderMessage(form);
        ArrayList list = (ArrayList) r.get("result");
        return list;
    }


}
