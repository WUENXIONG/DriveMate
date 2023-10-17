package com.mis.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.util.CosUtil;
import com.common.util.DataPagingDef;
import com.common.util.ResponseCodeMap;
import com.mis.controller.form.SearchDriverByPageForm;
import com.mis.controller.form.SearchDriverRealSummaryForm;
import com.mis.controller.form.UpdateDriverRealAuthForm;
import com.mis.feign.DriverServiceAPI;
import org.springframework.stereotype.Service;
import com.mis.service.DriverService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class DriverServiceImpl implements DriverService{
    @Resource
    private DriverServiceAPI driverServiceAPI;

    @Resource
    private CosUtil cosUtil;


    @Override
    public DataPagingDef searchDriverByPage(SearchDriverByPageForm form) {
        ResponseCodeMap r = driverServiceAPI.searchDriverByPage(form);
        HashMap map = (HashMap) r.get("result");
        DataPagingDef dataPagingDef = BeanUtil.toBean(map, DataPagingDef.class);
        return dataPagingDef;
    }

    @Override
    public HashMap searchDriverComprehensiveData(byte realAuth, Long driverId) {
        HashMap map = new HashMap();
        if (realAuth == 2 || realAuth == 3) {
            //查询司机实名信息摘要
            SearchDriverRealSummaryForm form_1 = new SearchDriverRealSummaryForm();
            form_1.setDriverId(driverId);
            ResponseCodeMap r = driverServiceAPI.searchDriverRealSummary(form_1);
            HashMap summaryMap = (HashMap) r.get("result");

            //获取私有读写文件的临时URL地址
            String idcardFront = MapUtil.getStr(summaryMap, "idcardFront");
            String idcardBack = MapUtil.getStr(summaryMap, "idcardBack");
            String idcardHolding = MapUtil.getStr(summaryMap, "idcardHolding");
            String drcardFront = MapUtil.getStr(summaryMap, "drcardFront");
            String drcardBack = MapUtil.getStr(summaryMap, "drcardBack");
            String drcardHolding = MapUtil.getStr(summaryMap, "drcardHolding");
            idcardFront = idcardFront.length() > 0 ? cosUtil.getPrivateFileUrl(idcardFront) : "";
            idcardBack = idcardBack.length() > 0 ? cosUtil.getPrivateFileUrl(idcardBack) : "";
            idcardHolding = idcardHolding.length() > 0 ? cosUtil.getPrivateFileUrl(idcardHolding) : "";
            drcardFront = drcardFront.length() > 0 ? cosUtil.getPrivateFileUrl(drcardFront) : "";
            drcardBack = drcardBack.length() > 0 ? cosUtil.getPrivateFileUrl(drcardBack) : "";
            drcardHolding = drcardHolding.length() > 0 ? cosUtil.getPrivateFileUrl(drcardHolding) : "";
            summaryMap.replace("idcardFront", idcardFront);
            summaryMap.replace("idcardBack", idcardBack);
            summaryMap.replace("idcardHolding", idcardHolding);
            summaryMap.replace("drcardFront", drcardFront);
            summaryMap.replace("drcardBack", drcardBack);
            summaryMap.replace("drcardHolding", drcardHolding);
            map.put("summaryMap", summaryMap);

            //TODO 这里以后还有很多要写的东西
        }
        return map;
    }

    @Override
    @Transactional
    @LcnTransaction
    public int updateDriverRealAuth(UpdateDriverRealAuthForm form) {
        ResponseCodeMap r = driverServiceAPI.updateDriverRealAuth(form);
        int rows = MapUtil.getInt(r, "rows");
        //TODO 此处应该调用消息子系统发送通知消息
        return rows;
    }

}

