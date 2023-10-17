package com.order.service.impl;

import cn.hutool.core.map.MapUtil;
import com.order.db.dao.OrderDao;
import com.order.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Override
    public HashMap searchDriverTodayBusinessData(long driverId) {

        HashMap result = orderDao.searchDriverTodayBusinessData(driverId);

        return result;
    }
}

