package com.driver.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.exception.DriveMateException;
import com.common.util.MicroAppUtil;
import com.driver.db.dao.DriverDao;
import com.driver.db.dao.DriverSettingsDao;
import com.driver.db.dao.WalletDao;
import com.driver.db.pojo.DriverSettingsEntity;
import com.driver.db.pojo.WalletEntity;
import com.driver.service.DriverService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.CreatePersonRequest;
import com.tencentcloudapi.iai.v20200303.models.CreatePersonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DriverServiceImpl implements DriverService {
    @Value("${tencent.cloud.secretId}")
    private String secretId;

    @Value("${tencent.cloud.secretKey}")
    private String secretKey;

    @Value("${tencent.cloud.face.groupName}")
    private String groupName;

    @Value("${tencent.cloud.face.region}")
    private String region;

    @Resource
    private MicroAppUtil microAppUtil;

    @Resource
    private DriverDao driverDao;

    @Resource
    private DriverSettingsDao settingsDao;

    @Resource
    private WalletDao walletDao;


    @Override
    @Transactional
    @LcnTransaction
    public String registerNewDriver(Map param) {
        String code = MapUtil.getStr(param,"code");
        String openId = microAppUtil.getOpenId(code);

        HashMap tmpParam = new HashMap<>(){
            {
              put("openId",openId);
            }
        };
        if(driverDao.hasDriver(tmpParam) != 0){
            throw new DriveMateException("该微信无法注册");
        }

        param.put("openId",openId);
        driverDao.registerNewDriver(param);
        String driverId = driverDao.searchDriverId(openId);

        DriverSettingsEntity settingsEntity = new DriverSettingsEntity();
        settingsEntity.setDriverId(Long.parseLong(driverId));
        JSONObject json = new JSONObject();
        json.set("orientation","");
        json.set("listenService",true);
        json.set("orderDistance",0);
        json.set("rangeDistance",5);
        json.set("autoAccept",false);
        settingsEntity.setSettings(json.toString());
        settingsDao.insertDriverSettings(settingsEntity);

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setDriverId(Long.parseLong(driverId));
        walletEntity.setBalance(new BigDecimal("0"));
        walletEntity.setPassword(null);
        walletDao.insert(walletEntity);

        return driverId;

    }

    @Override
    @Transactional
    @LcnTransaction
    public int updateDriverAuth(Map param){
        int rows = driverDao.updateDriverAuth(param);
        return rows;
    }

    @Override
    public String createDriverFaceModel(long driverId, String photo) {

        HashMap map = driverDao.searchDriverNameAndSex(driverId);
        String name = MapUtil.getStr(map,"name");
        String sex = MapUtil.getStr(map, "sex");

        //根据腾讯云API文档创建面部档案
        //https://console.cloud.tencent.com/api/explorer?Product=iai&Version=2020-03-03&Action=CreatePerson

        try {
            Credential cred = new Credential(secretId, secretKey);
            IaiClient client = new IaiClient(cred, region);
            CreatePersonRequest req = new CreatePersonRequest();
            req.setGroupId(groupName);
            req.setPersonId(driverId + "");
            long gender = sex.equals("男") ? 1L : 2L;
            req.setGender(gender);
            req.setQualityControl(4L);
            req.setUniquePersonControl(4L);
            req.setPersonName(name);
            req.setImage(photo);
            CreatePersonResponse resp = client.CreatePerson(req);

            if(StrUtil.isNotBlank(resp.getFaceId())){
                int rows = driverDao.updateDriverArchive(driverId);
                if(rows != 1){
                    return "更新司机归档字段失败";
                }
            }

        }catch (TencentCloudSDKException e){
            log.error("创建腾讯云端司机档案失败", e);
            return  "创建腾讯云端司机档案失败";
        }

        return "";
    }

    @Override
    public HashMap login(String code) {
        String openId = microAppUtil.getOpenId(code);
        HashMap result = driverDao.login(openId);
        if (result != null && result.containsKey("archive")) {
            int temp = MapUtil.getInt(result, "archive");
            boolean archive = temp == 1 ? true : false;
            result.replace("archive", archive);
        }
        return result;
    }


}
