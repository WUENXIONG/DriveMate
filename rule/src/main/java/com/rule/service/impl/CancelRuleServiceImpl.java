package com.rule.service.impl;

import com.common.exception.DriveMateException;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.rule.db.dao.CancelRuleDao;
import com.rule.db.pojo.CancelRuleEntity;
import com.rule.service.CancelRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class CancelRuleServiceImpl implements CancelRuleService{
    @Resource
    private CancelRuleDao cancelRuleDao;
    private static final Logger log = LoggerFactory.getLogger(CancelRuleServiceImpl.class);

    public CancelRuleServiceImpl() {
    }

    public HashMap calculateDriverCancelOrder(String status, int cancelNum, int acceptMinute, int waitingMinute, String msg) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", 1);
        hashMap.put("key", msg);
        CancelRuleEntity cancelRuleEntity = this.cancelRuleDao.searchCurrentRule(hashMap);
        String rule = cancelRuleEntity.getRule();
        ExpressRunner expressRunner = new ExpressRunner();
        DefaultContext defaultContext = new DefaultContext();
        defaultContext.put("status", status);
        defaultContext.put("cancelNum", cancelNum);
        defaultContext.put("acceptMinute", acceptMinute);
        defaultContext.put("waitingMinute", waitingMinute);

        try {
            HashMap hashMap2 = (HashMap)expressRunner.execute(rule, defaultContext, (List)null, true, false);
            hashMap2.put("cancelRuleId", cancelRuleEntity.getId());
            return hashMap2;
        } catch (Exception var12) {
            log.error("计算取消订单失败", var12);
            throw new DriveMateException("计算取消订单失败");
        }
    }

    public HashMap searchCancelRuleById(long a) {
        return this.cancelRuleDao.searchCancelRuleById(a);
    }
}
