package com.order.quarz.job;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.map.MapUtil;
import com.common.exception.DriveMateException;
import com.order.db.dao.OrderProfitsharingDao;
import com.order.quarz.QuartzUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Slf4j
public class HandleProfitsharingJob extends QuartzJobBean {

    @Resource
    private OrderProfitsharingDao profitsharingDao;

    @Resource
    private QuartzUtil quartzUtil;

    @Override
    @Transactional
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {

        //获取传给定时器的业务数据
        Map map = context.getJobDetail().getJobDataMap();
        String uuid = MapUtil.getStr(map, "uuid");
        String payId = MapUtil.getStr(map, "payId");

        //查询分账记录ID、分账金额
        map = profitsharingDao.searchDriverIncome(uuid);
        if (map == null || map.size() == 0) {
            log.error("没有查询到分账记录");
            return;
        }
//        String driverIncome = MapUtil.getStr(map, "driverIncome");
        long profitsharingId = MapUtil.getLong(map, "profitsharingId");


        String staus = "PROCESSING";

        if("FINISHED".equals(staus)){
            int rows = profitsharingDao.updateProfitsharingStatus(profitsharingId);
            if (rows != 1) {
                log.error("更新分账状态失败", new DriveMateException("更新分账状态失败"));
            }
        }else if("PROCESSING".equals(staus)){
            //如果状态是分账中，等待几分钟再查询分账结果
            JobDetail jobDetail = JobBuilder.newJob(SearchProfitsharingJob.class).build();
            Map dataMap = jobDetail.getJobDataMap();
//            dataMap.put("uuid", uuid);
            dataMap.put("profitsharingId", profitsharingId);
//            dataMap.put("payId", payId);

            Date executeDate = new DateTime().offset(DateField.MINUTE, 2);
            quartzUtil.addJob(jobDetail, uuid, "查询代驾单分账任务组", executeDate);

        }else{
            log.error("执行分账失败", new DriveMateException("执行分账失败"));
        }


    }


}
