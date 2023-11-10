package com.rule.service.impl;

import cn.hutool.core.map.MapUtil;
import com.common.exception.DriveMateException;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.rule.db.dao.AwardRuleDao;
import com.rule.db.dao.OrderDao;
import com.rule.db.pojo.AwardRuleEntity;
import com.rule.service.AwardRuleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AwardRuleImpl implements AwardRuleService {

    private static final Logger log = LoggerFactory.getLogger(AwardRuleImpl.class);
    @Resource
    private OrderDao orderDao;
    @Resource
    private AwardRuleDao awardRuleDao;

    public AwardRuleImpl() {
    }

    public String calculateIncentiveFee(long driverId, String acceptTime, String msg) {
        AwardRuleEntity awardRuleEntity = this.awardRuleDao.searchCurrentRule(msg);
        String rule = awardRuleEntity.getRule();
        ExpressRunner expressRunner = new ExpressRunner();
        DefaultContext defaultContext = new DefaultContext();
        defaultContext.put("flag", false);
        defaultContext.put("acceptTime", acceptTime);

        try {
            HashMap hashMap = (HashMap)expressRunner.execute(rule, defaultContext, (List)null, true, false);
            hashMap.put("cancelRuleId", awardRuleEntity.getId());
            if (hashMap.size() == 1) {
                String start = MapUtil.getStr(hashMap, "start");
                String var8 = MapUtil.getStr(hashMap, "end");
                HashMap hashMap2 = new HashMap();
                hashMap2.put("driverId", driverId);
                hashMap2.put("start", start);
                hashMap2.put("end", var8);
                long l = this.orderDao.searchFinishCountInRange(hashMap2);
                if (l == 0L) {
                    return "0.00";
                } else {
                    defaultContext.replace("flag", true);
                    defaultContext.put("real", l);
                    return (String)expressRunner.execute(rule, defaultContext, (List)null, true, false);
                }
            } else {
                return "0.00";
            }
        } catch (Exception var15) {
            log.error("计算系统奖励失败", var15);
            throw new DriveMateException("计算系统奖励失败");
        }
    }

}
