package com.order.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.common.util.DataPaging;
import com.common.util.ResponseCodeMap;
import com.order.controller.form.*;
import com.order.db.pojo.OrderBillEntity;
import com.order.db.pojo.OrderEntity;
import com.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/order")
@Tag(name = "OrderController", description = "订单模块Web接口")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/searchDriverTodayBusinessData")
    @Operation(summary = "查询司机当天营业数据")
    public ResponseCodeMap searchDriverTodayBusinessData(@RequestBody @Valid SearchDriverTodayBusinessDataForm form) {
        HashMap result = orderService.searchDriverTodayBusinessData(form.getDriverId());
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/insertOrder")
    @Operation(summary = "顾客下单")
    public ResponseCodeMap insertOrder(@RequestBody @Valid InsertOrderForm form) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUuid(form.getUuid());
        orderEntity.setCustomerId(form.getCustomerId());
        orderEntity.setStartPlace(form.getStartPlace());
        JSONObject json = new JSONObject();
        json.set("latitude", form.getStartPlaceLatitude());
        json.set("longitude", form.getStartPlaceLongitude());
        orderEntity.setStartPlaceLocation(json.toString());
        orderEntity.setEndPlace(form.getEndPlace());
        json = new JSONObject();
        json.set("latitude", form.getEndPlaceLatitude());
        json.set("longitude", form.getEndPlaceLongitude());
        orderEntity.setEndPlaceLocation(json.toString());
        orderEntity.setExpectsMileage(new BigDecimal(form.getExpectsMileage()));
        orderEntity.setExpectsFee(new BigDecimal(form.getExpectsFee()));
        orderEntity.setFavourFee(new BigDecimal(form.getFavourFee()));
        orderEntity.setChargeRuleId(form.getChargeRuleId());
        orderEntity.setCarPlate(form.getCarPlate());
        orderEntity.setCarType(form.getCarType());
        orderEntity.setDate(form.getDate());

        OrderBillEntity billEntity = new OrderBillEntity();
        billEntity.setBaseMileage(form.getBaseMileage());
        billEntity.setBaseMileagePrice(new BigDecimal(form.getBaseMileagePrice()));
        billEntity.setExceedMileagePrice(new BigDecimal(form.getExceedMileagePrice()));
        billEntity.setBaseMinute(form.getBaseMinute());
        billEntity.setExceedMinutePrice(new BigDecimal(form.getExceedMinutePrice()));
        billEntity.setBaseReturnMileage(form.getBaseReturnMileage());
        billEntity.setExceedReturnPrice(new BigDecimal(form.getExceedReturnPrice()));

        String id = orderService.insertOrder(orderEntity, billEntity);
        return ResponseCodeMap.ok().put("result", id);
    }


    @PostMapping("/acceptNewOrder")
    @Operation(summary = "司机接单")
    public ResponseCodeMap acceptNewOrder(@RequestBody @Valid AcceptNewOrderForm form) {
        String result = orderService.acceptNewOrder(form.getDriverId(), form.getOrderId());
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/searchDriverExecuteOrder")
    @Operation(summary = "查询司机正在执行的订单记录")
    public ResponseCodeMap searchDriverExecuteOrder(@RequestBody @Valid SearchDriverExecuteOrderForm form) {
        Map param = BeanUtil.beanToMap(form);
        HashMap map = orderService.searchDriverExecuteOrder(param);
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/searchOrderStatus")
    @Operation(summary = "查询订单状态")
    public ResponseCodeMap searchOrderStatus(@RequestBody @Valid SearchOrderStatusForm form) {
        Map param = BeanUtil.beanToMap(form);
        Integer status = orderService.searchOrderStatus(param);
        return ResponseCodeMap.ok().put("result", status);
    }

    @PostMapping("/deleteUnAcceptOrder")
    @Operation(summary = "删除没有司机接单的订单")
    public ResponseCodeMap deleteUnAcceptOrder(@RequestBody @Valid DeleteUnAcceptOrderForm form) {
        Map param = BeanUtil.beanToMap(form);
        String result = orderService.deleteUnAcceptOrder(param);
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/searchDriverCurrentOrder")
    @Operation(summary = "查询司机当前订单")
    public ResponseCodeMap searchDriverCurrentOrder(@RequestBody @Valid SearchDriverCurrentOrderForm form) {
        HashMap map = orderService.searchDriverCurrentOrder(form.getDriverId());
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/hasCustomerCurrentOrder")
    @Operation(summary = "查询乘客是否存在当前的订单")
    public ResponseCodeMap hasCustomerCurrentOrder(@RequestBody @Valid HasCustomerCurrentOrderForm form) {
        HashMap map = orderService.hasCustomerCurrentOrder(form.getCustomerId());
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/searchOrderForMoveById")
    @Operation(summary = "查询订单信息用于司乘同显功能")
    public ResponseCodeMap searchOrderForMoveById(@RequestBody @Valid SearchOrderForMoveByIdForm form) {
        Map param = BeanUtil.beanToMap(form);
        HashMap map = orderService.searchOrderForMoveById(param);
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/arriveStartPlace")
    @Operation(summary = "司机到达上车点")
    public ResponseCodeMap arriveStartPlace(@RequestBody @Valid ArriveStartPlaceForm form) {
        Map param = BeanUtil.beanToMap(form);
        param.put("status", 3);
        int rows = orderService.arriveStartPlace(param);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/confirmArriveStartPlace")
    @Operation(summary = "乘客确认司机到达上车点")
    public ResponseCodeMap confirmArriveStartPlace(@RequestBody @Valid ConfirmArriveStartPlaceForm form) {
        boolean result = orderService.confirmArriveStartPlace(form.getOrderId());
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/startDriving")
    @Operation(summary = "开始代驾")
    public ResponseCodeMap startDriving(@RequestBody @Valid StartDrivingForm form) {
        Map param = BeanUtil.beanToMap(form);
        param.put("status", 4);
        int rows = orderService.startDriving(param);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/updateOrderStatus")
    @Operation(summary = "更新订单状态")
    public ResponseCodeMap updateOrderStatus(@RequestBody @Valid UpdateOrderStatusForm form) {
        Map param = BeanUtil.beanToMap(form);
        int rows = orderService.updateOrderStatus(param);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @PostMapping("/searchOrderByPage")
    @Operation(summary = "查询订单分页记录")
    public ResponseCodeMap searchOrderByPage(@RequestBody @Valid SearchOrderByPageForm form) {
        Map param = BeanUtil.beanToMap(form);
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        param.put("start", start);
        DataPaging dataPaging = orderService.searchOrderByPage(param);
        return ResponseCodeMap.ok().put("result", dataPaging);
    }

    @PostMapping("/searchOrderContent")
    @Operation(summary = "查询订单详情")
    public ResponseCodeMap searchOrderContent(@RequestBody @Valid SearchOrderContentForm form) {
        Map map = orderService.searchOrderContent(form.getOrderId());
        return ResponseCodeMap.ok().put("result", map);
    }


    @PostMapping("/searchOrderStartLocationIn30Days")
    @Operation(summary = "查询30天以内订单上车点定位")
    public ResponseCodeMap searchOrderStartLocationIn30Days() {
        ArrayList<HashMap> result = orderService.searchOrderStartLocationIn30Days();
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/validDriverOwnOrder")
    @Operation(summary = "查询司机是否关联某订单")
    public ResponseCodeMap validDriverOwnOrder(@RequestBody @Valid ValidDriverOwnOrderForm form) {
        Map param = BeanUtil.beanToMap(form);
        boolean bool = orderService.validDriverOwnOrder(param);
        return ResponseCodeMap.ok().put("result", bool);
    }

    @PostMapping("/searchSettlementNeedData")
    @Operation(summary = "查询订单的开始和等时")
    public ResponseCodeMap searchSettlementNeedData(@RequestBody @Valid SearchSettlementNeedDataForm form) {
        HashMap map = orderService.searchSettlementNeedData(form.getOrderId());
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/searchOrderById")
    @Operation(summary = "根据ID查询订单信息")
    public ResponseCodeMap searchOrderById(@RequestBody @Valid SearchOrderByIdForm form) {
        Map param = BeanUtil.beanToMap(form);
        HashMap map = orderService.searchOrderById(param);
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/validCanPayOrder")
    @Operation(summary = "检查订单是否可以支付")
    public ResponseCodeMap validCanPayOrder(@RequestBody @Valid ValidCanPayOrderForm form) {
        Map param = BeanUtil.beanToMap(form);
        HashMap map = orderService.validCanPayOrder(param);
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/updateOrderPrepayId")
    @Operation(summary = "更新预支付订单ID")
    public ResponseCodeMap updateOrderPrepayId(@RequestBody @Valid UpdateOrderPrepayIdForm form) {
        Map param = BeanUtil.beanToMap(form);
        int rows = orderService.updateOrderPrepayId(param);
        return ResponseCodeMap.ok().put("rows", rows);
    }

    @RequestMapping("/receiveMessage")
    @Operation(summary = "接收代驾费消息通知")
    public void receiveMessage(@RequestBody @Valid receiveMessageForm form) throws Exception {


            String uuid = form.getUuId();
            long time = System.currentTimeMillis();
            int random = (int)(Math.random() * Integer.MAX_VALUE);
            String payId = new UUID(time, random) + "";;
            Long currTime = System.currentTimeMillis();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String payTime = format.format(currTime);

            //TODO 修改订单状态、执行分账、发放系统奖励
            orderService.handlePayment(uuid, payId, payTime);
    }

    @PostMapping("/updateOrderAboutPayment")
    @Operation(summary = "查询司机是否关联某订单")
    public ResponseCodeMap updateOrderAboutPayment(@RequestBody @Valid UpdateOrderAboutPaymentForm form) {
        Map param = BeanUtil.beanToMap(form);
        String result = orderService.updateOrderAboutPayment(param);
        return ResponseCodeMap.ok().put("result", result);
    }


}

