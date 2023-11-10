package com.rule.db.dao;

import java.util.Map;

public interface OrderDao {

    long searchFinishCountInDay(long var1);

    long searchFinishCountInRange(Map var1);

    long searchCancelCountInDay(long var1);
}
