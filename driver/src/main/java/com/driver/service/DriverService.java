package com.driver.service;

import com.common.util.DataPaging;

import java.util.HashMap;
import java.util.Map;

public interface DriverService {
    public String registerNewDriver(Map param);

    public int updateDriverAuth(Map param);

    public String createDriverFaceModel(long driverId, String photo);

    public HashMap login(String code);

    public HashMap searchDriverBaseInfo(long driverId);

    public DataPaging searchDriverByPage(Map param);

    public HashMap searchDriverAuth(long driverId);

    public HashMap searchDriverRealSummary(long driverId);

    public int updateDriverRealAuth(Map param);

    public HashMap searchDriverBriefInfo(long driverId);

}
