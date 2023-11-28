package com.order.db.dao;


import com.order.db.pojo.OrderEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface OrderDao {
    public HashMap searchDriverTodayBusinessData(long driverId);

    public int insert(OrderEntity entity);

    public String searchOrderIdByUUID(String uuid);

    public int acceptNewOrder(Map param);

    public HashMap searchDriverExecuteOrder(Map param);

    public Integer searchOrderStatus(Map param);

    public int deleteUnAcceptOrder(Map param);

    public HashMap searchDriverCurrentOrder(long driverId);

    public Long hasCustomerUnFinishedOrder(long customerId);

    public HashMap hasCustomerUnAcceptOrder(long customerId);

    public HashMap searchOrderForMoveById(Map param);

    public int updateOrderStatus(Map param);

}




