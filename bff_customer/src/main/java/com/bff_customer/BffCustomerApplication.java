package com.bff_customer;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("com.bff_customer.*")
@EnableDistributedTransaction
public class BffCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffCustomerApplication.class, args);
    }

}
