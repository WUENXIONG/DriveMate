package com.order.db.dao;


import com.order.db.pojo.OrderEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface OrderDao {
    public HashMap searchDriverTodayBusinessData(long driverId);
}




