package com.nebula.service;

import com.nebula.controller.vo.InsertOrderGpsVo;

import java.util.ArrayList;
import java.util.HashMap;

public interface OrderGpsService {
    public int insertOrderGps(ArrayList<InsertOrderGpsVo> list);

    public ArrayList<HashMap> searchOrderGps(long orderId);

    public HashMap searchOrderLastGps(long orderId);

    public String calculateOrderMileage(long orderId);

}
