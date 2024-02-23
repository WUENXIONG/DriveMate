package com.message_notification.db.dao;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.message_notification.db.pojo.MessageEntity;
import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

@Repository
public class MessageDao {
    @Resource
    private MongoTemplate mongoTemplate;

    public String insert(MessageEntity entity) {
        Date sendTime = entity.getSendTime();
        sendTime = DateUtil.offset(sendTime, DateField.HOUR, 8);
        entity.setSendTime(sendTime);
        entity = mongoTemplate.save(entity);
        return entity.get_id();
    }


    public HashMap searchMessageById(String id) {
        HashMap map = mongoTemplate.findById(id, HashMap.class, "message");
        Date sendTime = (Date) map.get("sendTime");
        sendTime = DateUtil.offset(sendTime, DateField.HOUR, -8);
        map.replace("sendTime", DateUtil.format(sendTime, "yyyy-MM-dd HH:mm"));
        return map;
    }

    public long deleteUserMessage(int receiverId, String identity) {
        Query query = new Query();
        query.addCriteria(Criteria.where("receiverId").is(receiverId).and("receiverIdentity").is(identity));
        DeleteResult result = mongoTemplate.remove(query, "message");
        long rows = result.getDeletedCount();
        return rows;
    }
}
