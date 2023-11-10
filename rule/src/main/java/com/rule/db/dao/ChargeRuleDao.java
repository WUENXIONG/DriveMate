package com.rule.db.dao;

import com.rule.db.pojo.ChargeRuleEntity;

import java.util.HashMap;

public interface ChargeRuleDao {
    int insert(ChargeRuleEntity var1);

    HashMap searchChargeRuleById(long var1);

    ChargeRuleEntity searchCurrentRule(String var1);
}
