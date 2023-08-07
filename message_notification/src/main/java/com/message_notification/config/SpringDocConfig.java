package com.message_notification.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(
                title = "message_notification",
                description = "代驾消息服务子系统",
                version = "1.0"
        )
)

@Configuration
public class SpringDocConfig {

}
