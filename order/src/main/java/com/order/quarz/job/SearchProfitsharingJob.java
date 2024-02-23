package com.order.quarz.job;

import cn.hutool.core.map.MapUtil;
import com.common.exception.DriveMateException;
import com.order.db.dao.OrderProfitsharingDao;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
public class SearchProfitsharingJob extends QuartzJobBean {

    @Resource
    private OrderProfitsharingDao profitsharingDao;

    @Override
    @Transactional
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {

        Map map = context.getJobDetail().getJobDataMap();
//        String uuid = MapUtil.getStr(map, "uuid");
        long profitsharingId = MapUtil.getLong(map, "profitsharingId");
//        String payId = MapUtil.getStr(map, "payId");

        String staus = "FINISHED";

        if("FINISHED".equals(staus)){
            int rows = profitsharingDao.updateProfitsharingStatus(profitsharingId);
            if (rows != 1) {
                log.error("更新分账状态失败", new DriveMateException("更新分账状态失败"));
            }
        }else {
            log.error("查询分账失败", new DriveMateException("查询分账失败"));
        }

    }
}