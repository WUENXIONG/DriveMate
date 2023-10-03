package com.bff_driver.service;

import com.bff_driver.controller.form.CreateDriverFaceModelForm;
import com.bff_driver.controller.form.LoginForm;
import com.bff_driver.controller.form.RegisterNewDriverForm;
import com.bff_driver.controller.form.UpdateDriverAuthForm;

import java.util.HashMap;

public interface DriverService {

    public long registerNewDriver(RegisterNewDriverForm form);

    public int updateDriverAuth(UpdateDriverAuthForm form);

    public String createDriverFaceModel(CreateDriverFaceModelForm form);

    public HashMap login(LoginForm form);

}
