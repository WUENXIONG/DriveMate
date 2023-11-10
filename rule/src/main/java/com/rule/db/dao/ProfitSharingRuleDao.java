package com.rule.db.dao;

import com.rule.db.pojo.ProfitSharingRuleEntity;

import java.util.HashMap;

public interface ProfitSharingRuleDao {
    HashMap searchProfitSharingRuleById(long var1);

    ProfitSharingRuleEntity searchCurrentRule(String var1);
}
