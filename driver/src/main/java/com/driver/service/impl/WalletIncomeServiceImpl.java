package com.driver.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.exception.DriveMateException;
import com.driver.db.dao.WalletDao;
import com.driver.db.dao.WalletIncomeDao;
import com.driver.db.pojo.WalletIncomeEntity;
import com.driver.service.WalletIncomeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class WalletIncomeServiceImpl implements WalletIncomeService {

    @Resource
    private WalletIncomeDao walletIncomeDao;

    @Resource
    private WalletDao walletDao;

    @Override
    @Transactional
    @LcnTransaction
    public int transfer(WalletIncomeEntity entity) {
        //添加转账记录
        int rows = walletIncomeDao.insert(entity);
        if (rows != 1) {
            throw new DriveMateException("添加转账记录失败");
        }

        HashMap param = new HashMap() {{
            put("driverId", entity.getDriverId());
            put("amount", entity.getAmount());
        }};

        //更新帐户余额
        rows = walletDao.updateWalletBalance(param);
        if (rows != 1) {
            throw new DriveMateException("更新钱包余额失败");
        }
        return rows;
    }


}
