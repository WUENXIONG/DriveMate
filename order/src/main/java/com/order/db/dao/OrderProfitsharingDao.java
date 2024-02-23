package com.order.db.dao;


import com.order.db.pojo.OrderProfitsharingEntity;

import java.util.HashMap;

public interface OrderProfitsharingDao {

    public int insert(OrderProfitsharingEntity entity);

    public HashMap searchDriverIncome(String uuid);

    public int updateProfitsharingStatus(long profitsharingId);


}




