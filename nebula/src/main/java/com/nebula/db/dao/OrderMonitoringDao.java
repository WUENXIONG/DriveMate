package com.nebula.db.dao;

import com.nebula.db.pojo.OrderMonitoringEntity;

import java.util.HashMap;

public interface OrderMonitoringDao {

    public int insert(long orderId);

    public HashMap searchOrderRecordsAndReviews(long orderId);

    public int updateOrderMonitoring(OrderMonitoringEntity entity);



}
