package com.order.db.dao;

import com.order.db.pojo.OrderBillEntity;

import java.util.HashMap;
import java.util.Map;

public interface OrderBillDao {

    public int insert(OrderBillEntity entity);

    public int deleteUnAcceptOrderBill(long orderId);

}




