package com.rule.db.dao;

import com.rule.db.pojo.CancelRuleEntity;

import java.util.HashMap;
import java.util.Map;

public interface CancelRuleDao {
    HashMap searchCancelRuleById(long var1);

    CancelRuleEntity searchCurrentRule(Map var1);
}
