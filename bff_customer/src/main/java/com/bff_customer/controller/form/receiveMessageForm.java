package com.bff_customer.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "接受订单支付需要的信息")
public class receiveMessageForm {

    @NotBlank(message = "uuId")
    @Schema(description = "订单序列号")
    private String uuId;

}
