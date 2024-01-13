package com.nebula.db.dao;

import com.nebula.db.pojo.OrderGpsEntity;

import java.util.ArrayList;
import java.util.HashMap;

public interface OrderGpsDao {
    public int insert(OrderGpsEntity entity);

    public ArrayList<HashMap> searchOrderGps(long orderId);

    public HashMap searchOrderLastGps(long orderId);

    public ArrayList<HashMap> searchOrderAllGps(long orderId);

}
