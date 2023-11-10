package com.order.service.impl;

import cn.hutool.core.map.MapUtil;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.exception.DriveMateException;
import com.order.db.dao.OrderBillDao;
import com.order.db.dao.OrderDao;
import com.order.db.pojo.OrderBillEntity;
import com.order.db.pojo.OrderEntity;
import com.order.service.OrderService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private OrderBillDao orderBillDao;

    @Resource
    private RedisTemplate redisTemplate;



    @Override
    public HashMap searchDriverTodayBusinessData(long driverId) {

        HashMap result = orderDao.searchDriverTodayBusinessData(driverId);

        return result;
    }

    @Override
    @Transactional
    @LcnTransaction
    public String insertOrder(OrderEntity orderEntity, OrderBillEntity billEntity) {
        //插入订单记录
        int rows = orderDao.insert(orderEntity);
        if (rows == 1) {
            String id = orderDao.searchOrderIdByUUID(orderEntity.getUuid());
            //插入订单费用记录
            billEntity.setOrderId(Long.parseLong(id));
            rows = orderBillDao.insert(billEntity);
            if (rows == 1) {
                //往Redis里面插入缓存，配合Redis事务用于司机抢单，避免多个司机同时抢单成功
                redisTemplate.opsForValue().set("order#" + id, "none");
                redisTemplate.expire("order#" + id, 16, TimeUnit.MINUTES);   //缓存15分钟
                return id;
            } else {
                throw new DriveMateException("保存新订单费用失败");
            }
        } else {
            throw new DriveMateException("保存新订单失败");
        }
    }

    @Override
    @Transactional
    @LcnTransaction
    public String acceptNewOrder(long driverId, long orderId) {
        //Redis不存在抢单的新订单就代表抢单失败
        if (!redisTemplate.hasKey("order#" + orderId)) {
            return "抢单失败";
        }
        //执行Redis事务
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                //获取新订单记录的Version
                operations.watch("order#" + orderId);
                //本地缓存Redis操作
                operations.multi();
                //把新订单缓存的Value设置成抢单司机的ID
                operations.opsForValue().set("order#" + orderId, driverId);
                //执行Redis事务，如果事务提交失败会自动抛出异常
                return operations.exec();

            }
        });
        //抢单成功之后，删除Redis中的新订单，避免让其他司机参与抢单
        redisTemplate.delete("order#" + orderId);
        //更新订单记录，添加上接单司机ID和接单时间
        HashMap param = new HashMap() {{
            put("driverId", driverId);
            put("orderId", orderId);
        }};
        int rows = orderDao.acceptNewOrder(param);
        if (rows != 1) {
            throw new DriveMateException("接单失败，无法更新订单记录");
        }
        return "接单成功";

    }

    @Override
    public HashMap searchDriverExecuteOrder(Map param) {
        HashMap map = orderDao.searchDriverExecuteOrder(param);
        return map;
    }

    @Override
    public Integer searchOrderStatus(Map param) {
        Integer status = orderDao.searchOrderStatus(param);
        if (status == null) {
            throw new DriveMateException("没有查询到数据，请核对查询条件");
        }
        return status;
    }

    @Override
    @Transactional
    @LcnTransaction
    public String deleteUnAcceptOrder(Map param) {
        long orderId = MapUtil.getLong(param, "orderId");
        if (!redisTemplate.hasKey("order#" + orderId)) {
            return "订单取消失败";
        }
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch("order#" + orderId);
                operations.multi();
                operations.opsForValue().set("order#" + orderId, "none");
                return operations.exec();
            }
        });

        redisTemplate.delete("order#" + orderId);
        int rows = orderDao.deleteUnAcceptOrder(param);
        if (rows != 1) {
            return "订单取消失败";
        }
        return "订单取消成功";
    }



}

