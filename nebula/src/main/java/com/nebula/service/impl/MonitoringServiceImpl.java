package com.nebula.service.impl;

import cn.hutool.core.util.IdUtil;
import com.common.exception.DriveMateException;
import com.nebula.db.dao.OrderMonitoringDao;
import com.nebula.db.dao.OrderVoiceTextDao;
import com.nebula.db.pojo.OrderVoiceTextEntity;
import com.nebula.service.MonitoringService;
import com.nebula.task.VoiceTextCheckTask;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
@Slf4j
public class MonitoringServiceImpl implements MonitoringService {


    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Resource
    private OrderVoiceTextDao orderVoiceTextDao;

    @Resource
    private OrderMonitoringDao orderMonitoringDao;

    @Resource
    private VoiceTextCheckTask voiceTextCheckTask;


    @Override
    @Transactional
    public void monitoring(MultipartFile file, String name, String text) {
        //把录音文件上传到Minio
        try {
            MinioClient client = new MinioClient.Builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
            client.putObject(
                    PutObjectArgs.builder().bucket("drivemate-record").object(name)
                            .stream(file.getInputStream(), -1, 20971520)
                            .contentType("audio/x-mpeg")
                            .build());
        } catch (Exception e) {
            log.error("上传代驾录音文件失败", e);
            throw new DriveMateException("上传代驾录音文件失败");
        }

        OrderVoiceTextEntity entity = new OrderVoiceTextEntity();

        //文件名格式例如:2156356656617-1.mp3，我们要解析出订单号
        String[] temp = name.substring(0, name.indexOf(".mp3")).split("-");
        Long orderId = Long.parseLong(temp[0]);

        String uuid = IdUtil.simpleUUID();
        entity.setUuid(uuid);
        entity.setOrderId(orderId);
        entity.setRecordFile(name);
        entity.setText(text);
        //把文稿保存到HBase
        int rows = orderVoiceTextDao.insert(entity);
        if (rows != 1) {
            throw new DriveMateException("保存录音文稿失败");
        }

        //TODO 执行文稿内容审查
        voiceTextCheckTask.checkText(orderId, text, uuid);

    }

    @Override
    @Transactional
    public int insertOrderMonitoring(long orderId) {
        int rows = orderMonitoringDao.insert(orderId);
        if (rows != 1) {
            throw new DriveMateException("添加订单监控摘要记录失败");
        }
        return rows;
    }



}

