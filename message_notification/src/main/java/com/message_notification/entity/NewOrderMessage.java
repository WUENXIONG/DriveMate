package com.message_notification.entity;

import lombok.Data;

@Data
public class NewOrderMessage {

    private String userId;

    private String orderId;

    private String from;

    private String to;

    private String expectsFee;

    private String mileage;

    private String minute;

    private String distance;

    private String favourFee;

}
