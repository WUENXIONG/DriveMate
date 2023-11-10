package com.rule.controller.form;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(description = "计算订单分账的表单")
public class CalculateProfitSharingForm {
    @Schema(description = "订单ID")
    @NotNull(message = "orderId不能为空")
    @Min(value = 1L, message = "orderId不能小于1")
    private Long orderId;

    @Schema(description = "待分账费用")
    @NotBlank(message = "amount不能为空")
    @Pattern(regexp = "^[1-9]\\d*\\.\\d{1,2}$|^0\\.\\d{1,2}$|^[1-9]\\d*$", message = "amount内容不正确")
    private String amount;

    public Long getOrderId() {
        return this.orderId;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public CalculateProfitSharingForm(Long orderId, String amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public CalculateProfitSharingForm() {
    }

    public String toString() {
        Long var10000 = this.getOrderId();
        return "CalculateProfitsharingForm(orderId=" + var10000 + ", amount=" + this.getAmount() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CalculateProfitSharingForm)) {
            return false;
        } else {
            CalculateProfitSharingForm other = (CalculateProfitSharingForm)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$orderId = this.getOrderId();
                Object other$orderId = other.getOrderId();
                if (this$orderId == null) {
                    if (other$orderId != null) {
                        return false;
                    }
                } else if (!this$orderId.equals(other$orderId)) {
                    return false;
                }

                Object this$amount = this.getAmount();
                Object other$amount = other.getAmount();
                if (this$amount == null) {
                    if (other$amount != null) {
                        return false;
                    }
                } else if (!this$amount.equals(other$amount)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof CalculateProfitSharingForm;
    }

    public int hashCode() {
        int result = 1;
        Object $orderId = this.getOrderId();
        result = result * 59 + ($orderId == null ? 43 : $orderId.hashCode());
        Object $amount = this.getAmount();
        result = result * 59 + ($amount == null ? 43 : $amount.hashCode());
        return result;
    }
}
