package com.message_notification.service;

import com.message_notification.db.pojo.MessageEntity;
import com.message_notification.db.pojo.MessageRefEntity;

import java.util.HashMap;

public interface MessageService {

    public String insertMessage(MessageEntity entity);

    public HashMap searchMessageById(String id);

    public String insertRef(MessageRefEntity entity);

    public long searchUnreadCount(long userId, String identity);

    public long searchLastCount(long userId, String identity);

    public long updateUnreadMessage(String id);

    public long deleteMessageRefById(String id);

    public long deleteUserMessageRef(long userId, String identity);


}
