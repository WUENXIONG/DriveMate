package com.rule.service.impl;

import com.common.exception.DriveMateException;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.rule.db.dao.OrderCommentDao;
import com.rule.db.dao.OrderDao;
import com.rule.db.dao.ProfitSharingRuleDao;
import com.rule.db.pojo.ProfitSharingRuleEntity;
import com.rule.service.ProfitSharingRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class ProfitSharingRuleServiceImpl implements ProfitSharingRuleService {
    private static final Logger log = LoggerFactory.getLogger(ProfitSharingRuleServiceImpl.class);
    @Resource
    private OrderDao orderDao;
    @Resource
    private ProfitSharingRuleDao profitSharingRuleDao;
    @Resource
    private OrderCommentDao orderCommentDao;

    public ProfitSharingRuleServiceImpl() {
    }

    public HashMap searchProfitsharingRuleById(long a) {
        return this.profitSharingRuleDao.searchProfitSharingRuleById(a);
    }

    public HashMap calculateProfitsharing(long orderId, String amount, String msg) {
        long cancelNum = this.orderDao.searchCancelCountInDay(orderId);
        long finishNum = this.orderDao.searchFinishCountInDay(orderId);
        long negativeNum = this.orderCommentDao.searchNegativeCountInDay(orderId);
        ProfitSharingRuleEntity profitSharingRuleEntity = this.profitSharingRuleDao.searchCurrentRule(msg);
        String rule = profitSharingRuleEntity.getRule();
        ExpressRunner expressRunner = new ExpressRunner();
        DefaultContext defaultContext = new DefaultContext();
        defaultContext.put("cancelNum", cancelNum);
        defaultContext.put("negativeNum", negativeNum);
        defaultContext.put("finishNum", finishNum);
        defaultContext.put("amount", amount);

        try {
            HashMap hashMap = (HashMap)expressRunner.execute(rule, defaultContext, (List)null, true, false);
            hashMap.put("ruleId", profitSharingRuleEntity.getId().toString());
            return hashMap;
        } catch (Exception var16) {
            log.error("计算订单分账失败", var16);
            throw new DriveMateException("计算订单分账失败");
        }
    }
}
