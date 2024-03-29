package com.bff_customer.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "更新支付订单ID的表单")
public class UpdateOrderPrepayIdForm {

    @Schema(description = "预支付订单ID")
    private String prepayId;

    @Schema(description = "订单ID")
    private Long orderId;
}

