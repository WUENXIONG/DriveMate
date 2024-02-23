package com.bff_customer.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import com.bff_customer.controller.form.*;
import com.bff_customer.feign.*;
import com.bff_customer.service.OrderService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.exception.DriveMateException;
import com.common.util.ResponseCodeMap;
import com.common.wx.pay.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderServiceAPI orderServiceAPI;

    @Resource
    private MapServiceAPI mapServiceAPI;

    @Resource
    private RuleServiceAPI ruleServiceAPI;

    @Resource
    private MessageNotifyAPI messageNotifyAPI;

    @Resource
    private DriverServiceAPI driverServiceAPI;

    @Resource
    private VoucherServiceAPI voucherServiceAPI;

    @Resource
    private CustomerServiceAPI customerServiceAPI;

    @Override
    @Transactional
    @LcnTransaction
    public HashMap createNewOrder(CreateNewOrderForm form) {
        Long customerId = form.getCustomerId();
        String startPlace = form.getStartPlace();
        String startPlaceLatitude = form.getStartPlaceLatitude();
        String startPlaceLongitude = form.getStartPlaceLongitude();
        String endPlace = form.getEndPlace();
        String endPlaceLatitude = form.getEndPlaceLatitude();
        String endPlaceLongitude = form.getEndPlaceLongitude();
        String favourFee = form.getFavourFee();
        /**
         * 【重新预估里程和时间】
         * 虽然下单前，系统会预估里程和时长，但是有可能顾客在下单页面停留时间过长，
         * 然后再按下单键，这时候路线和时长可能都有变化，所以需要重新预估里程和时间
         */
        EstimateOrderMileageAndMinuteForm form_1 = new EstimateOrderMileageAndMinuteForm();
        form_1.setMode("driving");
        form_1.setStartPlaceLatitude(startPlaceLatitude);
        form_1.setStartPlaceLongitude(startPlaceLongitude);
        form_1.setEndPlaceLatitude(endPlaceLatitude);
        form_1.setEndPlaceLongitude(endPlaceLongitude);
        ResponseCodeMap r = mapServiceAPI.estimateOrderMileageAndMinute(form_1);
        HashMap map = (HashMap) r.get("result");
        String mileage = MapUtil.getStr(map, "mileage");
        int minute = MapUtil.getInt(map, "minute");

        /**
         * 重新估算订单金额
         */
        EstimateOrderChargeForm form_2 = new EstimateOrderChargeForm();
        form_2.setMileage(mileage);
        form_2.setTime(new DateTime().toTimeStr());
        r = ruleServiceAPI.estimateOrderCharge(form_2);
        map = (HashMap) r.get("result");
        String expectsFee = MapUtil.getStr(map, "amount");
        String chargeRuleId = MapUtil.getStr(map, "chargeRuleId");
        short baseMileage = MapUtil.getShort(map, "baseMileage");
        String baseMileagePrice = MapUtil.getStr(map, "baseMileagePrice");
        String exceedMileagePrice = MapUtil.getStr(map, "exceedMileagePrice");
        short baseMinute = MapUtil.getShort(map, "baseMinute");
        String exceedMinutePrice = MapUtil.getStr(map, "exceedMinutePrice");
        short baseReturnMileage = MapUtil.getShort(map, "baseReturnMileage");
        String exceedReturnPrice = MapUtil.getStr(map, "exceedReturnPrice");


        SearchBefittingDriverAboutOrderForm form_3 = new SearchBefittingDriverAboutOrderForm();
        form_3.setStartPlaceLatitude(startPlaceLatitude);
        form_3.setStartPlaceLongitude(startPlaceLongitude);
        form_3.setEndPlaceLatitude(endPlaceLatitude);
        form_3.setEndPlaceLongitude(endPlaceLongitude);
        form_3.setMileage(mileage);
        r = mapServiceAPI.searchBefittingDriverAboutOrder(form_3);
        ArrayList<HashMap> list = (ArrayList<HashMap>) r.get("result");

        HashMap result=new HashMap(){{
            put("count",0);
        }};


        /**
         * 生成订单记录
         */
        if(list.size() > 0){
            InsertOrderForm form_4 = new InsertOrderForm();
            //UUID字符串，充当订单号，微信支付时候会用上
            form_4.setUuid(IdUtil.simpleUUID());
            form_4.setCustomerId(customerId);
            form_4.setStartPlace(startPlace);
            form_4.setStartPlaceLatitude(startPlaceLatitude);
            form_4.setStartPlaceLongitude(startPlaceLongitude);
            form_4.setEndPlace(endPlace);
            form_4.setEndPlaceLatitude(endPlaceLatitude);
            form_4.setEndPlaceLongitude(endPlaceLongitude);
            form_4.setExpectsMileage(mileage);
            form_4.setExpectsFee(expectsFee);
            form_4.setFavourFee(favourFee);
            form_4.setDate(new DateTime().toDateStr());
            form_4.setChargeRuleId(Long.parseLong(chargeRuleId));
            form_4.setCarPlate(form.getCarPlate());
            form_4.setCarType(form.getCarType());
            form_4.setBaseMileage(baseMileage);
            form_4.setBaseMileagePrice(baseMileagePrice);
            form_4.setExceedMileagePrice(exceedMileagePrice);
            form_4.setBaseMinute(baseMinute);
            form_4.setExceedMinutePrice(exceedMinutePrice);
            form_4.setBaseReturnMileage(baseReturnMileage);
            form_4.setExceedReturnPrice(exceedReturnPrice);

            r = orderServiceAPI.insertOrder(form_4);
            String orderId = MapUtil.getStr(r, "result");


            /*
             * 发送新订单通知给相关司机
             */
            SendNewOrderMessageForm form_5 = new SendNewOrderMessageForm();
            String[] driverContent = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                HashMap one = list.get(i);
                String driverId = MapUtil.getStr(one, "driverId");
                String distance = MapUtil.getStr(one, "distance");
                distance = new BigDecimal(distance).setScale(1, RoundingMode.CEILING).toString();
                driverContent[i] = driverId + "#" + distance;
            }
            form_5.setDriversContent(driverContent);
            form_5.setOrderId(Long.parseLong(orderId));
            form_5.setFrom(startPlace);
            form_5.setTo(endPlace);
            form_5.setExpectsFee(expectsFee);
            //里程转化成保留小数点后一位
            mileage = new BigDecimal(mileage).setScale(1, RoundingMode.CEILING).toString();
            form_5.setMileage(mileage);
            form_5.setMinute(minute);
            form_5.setFavourFee(favourFee);
            messageNotifyAPI.sendNewOrderMessageAsync(form_5); //异步发送消息


            result.put("orderId",orderId);
            result.replace("count",list.size());

        }


        return result;

    }


    @Override
    public Integer searchOrderStatus(SearchOrderStatusForm form) {
        ResponseCodeMap r = orderServiceAPI.searchOrderStatus(form);
        Integer status = MapUtil.getInt(r, "result");
        return status;
    }

    @Override
    @Transactional
    @LcnTransaction
    public String deleteUnAcceptOrder(DeleteUnAcceptOrderForm form) {
        ResponseCodeMap r = orderServiceAPI.deleteUnAcceptOrder(form);
        String result = MapUtil.getStr(r, "result");
        return result;
    }

    @Override
    public HashMap searchOrderForMoveById(SearchOrderForMoveByIdForm form) {
        ResponseCodeMap r = orderServiceAPI.searchOrderForMoveById(form);
        HashMap map = (HashMap) r.get("result");
        return map;
    }

    @Override
    public HashMap hasCustomerCurrentOrder(HasCustomerCurrentOrderForm form) {
        ResponseCodeMap r = orderServiceAPI.hasCustomerCurrentOrder(form);
        HashMap map = (HashMap) r.get("result");
        return map;
    }

    @Override
    public boolean confirmArriveStartPlace(ConfirmArriveStartPlaceForm form) {
        ResponseCodeMap r = orderServiceAPI.confirmArriveStartPlace(form);
        boolean result = MapUtil.getBool(r, "result");
        return result;
    }

    @Override
    public HashMap searchOrderById(SearchOrderByIdForm form) {
        ResponseCodeMap r = orderServiceAPI.searchOrderById(form);
        HashMap map = (HashMap) r.get("result");
        Long driverId = MapUtil.getLong(map, "driverId");
        if(driverId!=null){
            SearchDriverBriefInfoForm infoForm = new SearchDriverBriefInfoForm();
            infoForm.setDriverId(driverId);
            r = driverServiceAPI.searchDriverBriefInfo(infoForm);
            HashMap temp = (HashMap) r.get("result");
            map.putAll(temp);
            return map;
        }
        return null;
    }

    @Override
    @Transactional
    @LcnTransaction
    public HashMap createWxPayment(long orderId, long customerId, Long voucherId) {
        /*
         * 1.先查询订单是否为6状态，其他状态都不可以生成支付订单
         */
        ValidCanPayOrderForm form_1 = new ValidCanPayOrderForm();
        form_1.setOrderId(orderId);
        form_1.setCustomerId(customerId);
        ResponseCodeMap r = orderServiceAPI.validCanPayOrder(form_1);
        HashMap map = (HashMap) r.get("result");
        String amount = MapUtil.getStr(map, "realFee");
        String uuid = MapUtil.getStr(map, "uuid");
        long driverId = MapUtil.getLong(map, "driverId");
        String discount = "0.00";
        if (voucherId != null) {
            /*
             * 2.查询代金券是否可以使用，并绑定
             */
            UseVoucherForm form_2 = new UseVoucherForm();
            form_2.setCustomerId(customerId);
            form_2.setVoucherId(voucherId);
            form_2.setOrderId(orderId);
            form_2.setAmount(amount);
            r = voucherServiceAPI.useVoucher(form_2);
            discount = MapUtil.getStr(r, "result");
        }
        if (new BigDecimal(amount).compareTo(new BigDecimal(discount)) == -1) {
            throw new DriveMateException("总金额不能小于优惠劵面额");
        }
        /*
         * 3.修改实付金额
         */
        amount = NumberUtil.sub(amount, discount).toString();
        UpdateBillPaymentForm form_3 = new UpdateBillPaymentForm();
        form_3.setOrderId(orderId);
        form_3.setRealPay(amount);
        form_3.setVoucherFee(discount);
        orderServiceAPI.updateBillPayment(form_3);

        /*
         * 4.查询用户的OpenID字符串
         */
        SearchCustomerOpenIdForm form_4 = new SearchCustomerOpenIdForm();
        form_4.setCustomerId(customerId);
        r = customerServiceAPI.searchCustomerOpenId(form_4);
        String customerOpenId = MapUtil.getStr(r, "result");

        /*
         * 5.查询司机的OpenId字符串
         */
        SearchDriverOpenIdForm form_5 = new SearchDriverOpenIdForm();
        form_5.setDriverId(driverId);
        r = driverServiceAPI.searchDriverOpenId(form_5);
        String driverOpenId = MapUtil.getStr(r, "result");

        /*
         * 6.TODO 创建支付订单
         *
         */
        try {
            //这里本需要调用微信支付接口，但无资质，遂作罢
            long time = System.currentTimeMillis();
            int random = (int)(Math.random() * Integer.MAX_VALUE);
            String prepayId = new UUID(time, random) + "";

            UpdateOrderPrepayIdForm form_6 = new UpdateOrderPrepayIdForm();
            form_6.setOrderId(orderId);
            form_6.setPrepayId(prepayId);
            orderServiceAPI.updateOrderPrepayId(form_6);

            String timeStamp = new Date().getTime() + "";
            String nonceStr = WXPayUtil.generateNonceStr();
            String paySign = "paySign-signature";

            map.clear();
            map.put("package", "prepay_id=" + prepayId);
            map.put("timeStamp", timeStamp);
            map.put("nonceStr", nonceStr);
            map.put("paySign", paySign);
            //uuid用于付款成功后，移动端主动请求更新充值状态
            map.put("uuid", uuid);
            map.put("driverOpenId",driverOpenId);

            receiveMessageForm form_7 = new receiveMessageForm();
            form_7.setUuId(uuid);
            orderServiceAPI.receiveMessage(form_7);

            return map;

        }catch (Exception e){
            log.error("创建支付订单失败", e);
            throw new DriveMateException("创建支付订单失败");
        }

    }

    @Override
    @Transactional
    @LcnTransaction
    public String updateOrderAboutPayment(UpdateOrderAboutPaymentForm form) {
        ResponseCodeMap r = orderServiceAPI.updateOrderAboutPayment(form);
        String result = MapUtil.getStr(r, "result");
        return result;
    }


}

