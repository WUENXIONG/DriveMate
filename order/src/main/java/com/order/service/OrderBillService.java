package com.order.service;

import java.util.HashMap;
import java.util.Map;

public interface OrderBillService {

    public int updateBillFee(Map param);

    public HashMap searchReviewDriverOrderBill(Map param);

    public int updateBillPayment(Map param);
}
