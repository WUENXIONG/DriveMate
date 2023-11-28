package com.bff_driver.controller;

import com.bff_driver.feign.NebulaServiceAPI;
import com.common.exception.DriveMateException;
import com.common.util.ResponseCodeMap;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/monitoring")
@Tag(name = "MonitoringController", description = "订单监控服务的Web接口")
@Slf4j
public class MonitoringController {

    @Resource
    private NebulaServiceAPI nebulaServiceAPI;

    @PostMapping(value = "/uploadRecordFile")
    public ResponseCodeMap uploadRecordFile(@RequestPart("file") MultipartFile file,
                                            @RequestPart("name") String name,
                                            @RequestPart(value = "text", required = false) String text) {
        if (file.isEmpty()) {
            throw new DriveMateException("上传文件不能为空");
        }
        nebulaServiceAPI.uploadRecordFile(file, name, text);

        return ResponseCodeMap.ok();
    }
}

