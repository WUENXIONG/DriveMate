package com.order_service.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(
                title = "order_service",
                description = "代驾订单服务子系统",
                version = "1.0"
        )
)

@Configuration
public class SpringDocConfig {

}
