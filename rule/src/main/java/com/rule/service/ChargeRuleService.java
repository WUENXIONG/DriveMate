package com.rule.service;

import com.rule.db.pojo.ChargeRuleEntity;

import java.util.HashMap;

public interface ChargeRuleService {
    HashMap searchChargeRuleById(long var1);

    HashMap calculateOrderCharge(String var1, String var2, int var3, String var4);

    int insert(ChargeRuleEntity var1);
}
