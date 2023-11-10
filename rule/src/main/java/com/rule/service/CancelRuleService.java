package com.rule.service;

import java.util.HashMap;

public interface CancelRuleService {

    HashMap searchCancelRuleById(long var1);

    HashMap calculateDriverCancelOrder(String var1, int var2, int var3, int var4, String var5);

}
