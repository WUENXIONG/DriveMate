package com.rule;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan
@MapperScan({"com.rule.db.dao"})
@ComponentScan({"com.rule.*"})
@ComponentScan({"com.common.*"})
@EnableDistributedTransaction
public class RuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuleApplication.class, args);
    }

}
