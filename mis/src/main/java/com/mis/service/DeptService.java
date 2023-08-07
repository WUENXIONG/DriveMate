package com.mis.service;

import com.common.util.DataPagingDef;
import com.mis.db.pojo.DeptEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface DeptService {
    public ArrayList<HashMap> searchAllDept();

    public DataPagingDef searchDeptByPage(Map param);

    public HashMap searchById(int id);

    public int insert(DeptEntity dept);

    public int update(DeptEntity dept);

    public int deleteDeptByIds(Integer[] ids);
}
