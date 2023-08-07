package com.voucher.db.dao;


import com.voucher.db.pojo.VoucherEntity;

import java.util.ArrayList;

public interface VoucherDao {
    public int insert(VoucherEntity entity);

    public ArrayList<String> searchIdByUUID(ArrayList<String> list);
}




