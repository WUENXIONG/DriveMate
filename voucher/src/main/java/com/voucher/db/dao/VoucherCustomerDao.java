package com.voucher.db.dao;


import com.voucher.db.pojo.VoucherCustomerEntity;

import java.util.Map;

public interface VoucherCustomerDao {
    public int insert(VoucherCustomerEntity entity);

    public String validCanUseVoucher(Map param);

    public int bindVoucher(Map param);
}




