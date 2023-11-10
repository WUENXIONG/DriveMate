package com.bff_driver.service;

import com.bff_driver.controller.form.ClearNewOrderQueueForm;
import com.bff_driver.controller.form.ReceiveNewOrderMessageForm;

import java.util.ArrayList;

public interface NewOrderMessageService {

    public void clearNewOrderQueue(ClearNewOrderQueueForm form);

    public ArrayList receiveNewOrderMessage(ReceiveNewOrderMessageForm form);

}
