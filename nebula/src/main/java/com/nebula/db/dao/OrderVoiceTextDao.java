package com.nebula.db.dao;

import com.nebula.db.pojo.OrderVoiceTextEntity;

import java.util.Map;

public interface OrderVoiceTextDao {

    public int insert(OrderVoiceTextEntity entity);

    public Long searchIdByUuid(String uuid);

    public int updateCheckResult(Map param);



}
