package com.order.service.impl;

import cn.hutool.core.map.MapUtil;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.exception.DriveMateException;
import com.order.db.dao.OrderBillDao;
import com.order.db.dao.OrderDao;
import com.order.db.dao.OrderProfitsharingDao;
import com.order.db.pojo.OrderProfitsharingEntity;
import com.order.service.OrderBillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderBillServiceImpl implements OrderBillService {

    @Resource
    OrderBillDao orderBillDao;

    @Resource
    OrderDao orderDao;

    @Resource
    OrderProfitsharingDao profitsharingDao;

    @Override
    @Transactional
    @LcnTransaction
    public int updateBillFee(Map param) {
        //更新账单数据
        int rows = orderBillDao.updateBillFee(param);
        if (rows != 1) {
            throw new DriveMateException("更新账单费用详情失败");
        }

        //更新订单数据
        rows = orderDao.updateOrderMileageAndFee(param);
        if (rows != 1) {
            throw new DriveMateException("更新订单费用详情失败");
        }

        //添加分账单数据
        OrderProfitsharingEntity entity = new OrderProfitsharingEntity();
        entity.setOrderId(MapUtil.getLong(param, "orderId"));
        entity.setRuleId(MapUtil.getLong(param, "ruleId"));
        entity.setAmountFee(new BigDecimal((String) param.get("total")));
        entity.setPaymentRate(new BigDecimal((String) param.get("paymentRate")));
        entity.setPaymentFee(new BigDecimal((String) param.get("paymentFee")));
        entity.setTaxRate(new BigDecimal((String) param.get("taxRate")));
        entity.setTaxFee(new BigDecimal((String) param.get("taxFee")));
        entity.setSystemIncome(new BigDecimal((String) param.get("systemIncome")));
        entity.setDriverIncome(new BigDecimal((String) param.get("driverIncome")));
        rows = profitsharingDao.insert(entity);
        if (rows != 1) {
            throw new DriveMateException("添加分账记录失败");
        }
        return rows;
    }

    @Override
    public HashMap searchReviewDriverOrderBill(Map param) {
        HashMap map = orderBillDao.searchReviewDriverOrderBill(param);
        return map;
    }

    @Override
    @Transactional
    @LcnTransaction
    public int updateBillPayment(Map param) {
        int rows = orderBillDao.updateBillPayment(param);
        if (rows != 1) {
            throw new DriveMateException("更新账单实际支付费用失败");
        }
        return rows;
    }



}
