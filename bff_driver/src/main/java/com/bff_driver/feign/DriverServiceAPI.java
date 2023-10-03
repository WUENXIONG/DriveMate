package com.bff_driver.feign;

import com.bff_driver.controller.form.CreateDriverFaceModelForm;
import com.bff_driver.controller.form.LoginForm;
import com.bff_driver.controller.form.RegisterNewDriverForm;
import com.bff_driver.controller.form.UpdateDriverAuthForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "driver")
public interface DriverServiceAPI {
    @PostMapping("/driver/registerNewDriver")
    public ResponseCodeMap registerNewDriver(RegisterNewDriverForm form);

    @PostMapping("driver/updateDriverAuth")
    public ResponseCodeMap updateDriverAuth(UpdateDriverAuthForm form);

    @PostMapping("/driver/createDriverFaceModel")
    public ResponseCodeMap createDiverFaceModel(CreateDriverFaceModelForm form);

    @PostMapping("/driver/login")
    public ResponseCodeMap login(LoginForm form);


}
