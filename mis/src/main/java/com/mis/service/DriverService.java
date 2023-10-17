package com.mis.service;

import com.common.util.DataPagingDef;
import com.mis.controller.form.SearchDriverByPageForm;
import com.mis.controller.form.UpdateDriverRealAuthForm;

import java.util.HashMap;

public interface DriverService {

    public DataPagingDef searchDriverByPage(SearchDriverByPageForm form);

    public HashMap searchDriverComprehensiveData(byte realAuth, Long driverId);

    public int updateDriverRealAuth(UpdateDriverRealAuthForm form);
}
