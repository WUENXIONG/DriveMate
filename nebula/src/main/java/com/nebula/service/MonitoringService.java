package com.nebula.service;

import org.springframework.web.multipart.MultipartFile;

public interface MonitoringService {

    public void monitoring(MultipartFile file, String name, String text);

    public int insertOrderMonitoring(long orderId);
}
