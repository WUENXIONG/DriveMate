package com.driver.db.dao;


import com.driver.db.pojo.WalletEntity;

import java.util.Map;

public interface WalletDao {

    public int insert(WalletEntity entity);

    public int updateWalletBalance(Map param);

}




