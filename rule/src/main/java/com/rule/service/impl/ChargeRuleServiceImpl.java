package com.rule.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.exception.DriveMateException;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.rule.db.dao.ChargeRuleDao;
import com.rule.db.pojo.ChargeRuleEntity;
import com.rule.service.ChargeRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class ChargeRuleServiceImpl implements ChargeRuleService {
    private static final Logger log = LoggerFactory.getLogger(ChargeRuleServiceImpl.class);
    @Resource
    private ChargeRuleDao chargeRuleDao;

    public ChargeRuleServiceImpl() {
    }

    public HashMap searchChargeRuleById(long ruleId) {
        return this.chargeRuleDao.searchChargeRuleById(ruleId);
    }

    @Transactional
    @LcnTransaction
    public int insert(ChargeRuleEntity chargeRuleEntity) {
        return this.chargeRuleDao.insert(chargeRuleEntity);
    }

    public HashMap calculateOrderCharge(String mileage, String time, int minute, String msg) {
        ChargeRuleEntity chargeRuleEntity = this.chargeRuleDao.searchCurrentRule(msg);
        String rule = chargeRuleEntity.getRule();
        ExpressRunner expressRunner = new ExpressRunner();
        DefaultContext defaultContext = new DefaultContext();
        defaultContext.put("mileage", mileage);
        defaultContext.put("time", time);
        defaultContext.put("minute", minute);

        try {
            HashMap hashMap = (HashMap)expressRunner.execute(rule, defaultContext, (List)null, true, false);
            hashMap.put("chargeRuleId", chargeRuleEntity.getId());
            return hashMap;
        } catch (Exception var10) {
            log.error("计算代驾费用失败", var10);
            throw new DriveMateException("计算代驾费用失败");
        }
    }
}
