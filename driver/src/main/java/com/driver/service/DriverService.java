package com.driver.service;

import com.common.util.DataPagingDef;
import com.esotericsoftware.reflectasm.PublicConstructorAccess;

import java.util.HashMap;
import java.util.Map;

public interface DriverService {
    public String registerNewDriver(Map param);

    public int updateDriverAuth(Map param);

    public String createDriverFaceModel(long driverId, String photo);

    public HashMap login(String code);

    public HashMap searchDriverBaseInfo(long driverId);

    public DataPagingDef searchDriverByPage(Map param);

    public HashMap searchDriverAuth(long driverId);

    public HashMap searchDriverRealSummary(long driverId);

    public int updateDriverRealAuth(Map param);

}
