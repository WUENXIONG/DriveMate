package com.order.db.dao;

import com.order.db.pojo.OrderBillEntity;

import java.util.HashMap;
import java.util.Map;

public interface OrderBillDao {

    public int insert(OrderBillEntity entity);

    public int deleteUnAcceptOrderBill(long orderId);

    public int updateBillFee(Map param);

    public HashMap searchReviewDriverOrderBill(Map param);

    public int updateBillPayment(Map param);
}




