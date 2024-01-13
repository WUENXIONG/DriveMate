package com.mis.service;

import com.common.util.DataPaging;
import com.mis.controller.form.SearchOrderByPageForm;
import com.mis.controller.form.SearchOrderLastGpsForm;

import java.util.ArrayList;
import java.util.HashMap;

public interface OrderService {

    public DataPaging searchOrderByPage(SearchOrderByPageForm form);

    public HashMap searchOrderComprehensiveInfo(long orderId);

    public HashMap searchOrderLastGps(SearchOrderLastGpsForm form);

    public ArrayList<HashMap> searchOrderStartLocationIn30Days();
}
