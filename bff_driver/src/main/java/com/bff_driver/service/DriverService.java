package com.bff_driver.service;

import com.bff_driver.controller.form.*;

import java.util.HashMap;

public interface DriverService {

    public long registerNewDriver(RegisterNewDriverForm form);

    public int updateDriverAuth(UpdateDriverAuthForm form);

    public String createDriverFaceModel(CreateDriverFaceModelForm form);

    public HashMap login(LoginForm form);

    public HashMap searchDriverBaseInfo(SearchDriverBaseInfoForm form);

    public HashMap searchWorkbenchData(long driverId);

    public HashMap searchDriverAuth(SearchDriverAuthForm form);

}
