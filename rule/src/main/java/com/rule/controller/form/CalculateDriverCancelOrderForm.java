package com.rule.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(description = "计算司机取消订单的表单")
public class CalculateDriverCancelOrderForm {
    @Schema(description = "司机等时分钟数")
    @NotNull(message = "waitingMinute不能为空")
    @Min(value = 0L, message = "waitingMinute不能小于0")
    private Short waitingMinute;

    @Schema(description = "司机当天取消订单次数")
    @NotNull(message = "cancelNum不能为空")
    @Min(value = 0L, message = "cancelNum不能小于0")
    private Short cancelNum;

    @Schema(description = "接单口到当前的分钟数")
    @NotNull(message = "acceptMinute不能为空")
    @Min(value = 0L, message = "acceptMinute不能小于0")
    private Short acceptMinute;

    @Schema(description = "状态")
    @NotBlank(message = "status不能为空")
    @Pattern(regexp = "^未到达代驾点$||^到达代驾点$||^开始代驾$")
    private String status;

    public Short getWaitingMinute() {
        return this.waitingMinute;
    }

    public Short getCancelNum() {
        return this.cancelNum;
    }

    public Short getAcceptMinute() {
        return this.acceptMinute;
    }

    public String getStatus() {
        return this.status;
    }

    public void setWaitingMinute(Short waitingMinute) {
        this.waitingMinute = waitingMinute;
    }

    public void setCancelNum(Short cancelNum) {
        this.cancelNum = cancelNum;
    }

    public void setAcceptMinute(Short acceptMinute) {
        this.acceptMinute = acceptMinute;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CalculateDriverCancelOrderForm(Short waitingMinute, Short cancelNum, Short acceptMinute, String status) {
        this.waitingMinute = waitingMinute;
        this.cancelNum = cancelNum;
        this.acceptMinute = acceptMinute;
        this.status = status;
    }

    public CalculateDriverCancelOrderForm() {
    }

    public String toString() {
        Short var10000 = this.getWaitingMinute();
        return "CalculateDriverCancelOrderForm(waitingMinute=" + var10000 + ", cancelNum=" + this.getCancelNum() + ", acceptMinute=" + this.getAcceptMinute() + ", status=" + this.getStatus() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CalculateDriverCancelOrderForm)) {
            return false;
        } else {
            CalculateDriverCancelOrderForm other = (CalculateDriverCancelOrderForm)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$waitingMinute = this.getWaitingMinute();
                    Object other$waitingMinute = other.getWaitingMinute();
                    if (this$waitingMinute == null) {
                        if (other$waitingMinute == null) {
                            break label59;
                        }
                    } else if (this$waitingMinute.equals(other$waitingMinute)) {
                        break label59;
                    }

                    return false;
                }

                Object this$cancelNum = this.getCancelNum();
                Object other$cancelNum = other.getCancelNum();
                if (this$cancelNum == null) {
                    if (other$cancelNum != null) {
                        return false;
                    }
                } else if (!this$cancelNum.equals(other$cancelNum)) {
                    return false;
                }

                Object this$acceptMinute = this.getAcceptMinute();
                Object other$acceptMinute = other.getAcceptMinute();
                if (this$acceptMinute == null) {
                    if (other$acceptMinute != null) {
                        return false;
                    }
                } else if (!this$acceptMinute.equals(other$acceptMinute)) {
                    return false;
                }

                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof CalculateDriverCancelOrderForm;
    }

    public int hashCode() {
        int result = 1;
        Object $waitingMinute = this.getWaitingMinute();
        result = result * 59 + ($waitingMinute == null ? 43 : $waitingMinute.hashCode());
        Object $cancelNum = this.getCancelNum();
        result = result * 59 + ($cancelNum == null ? 43 : $cancelNum.hashCode());
        Object $acceptMinute = this.getAcceptMinute();
        result = result * 59 + ($acceptMinute == null ? 43 : $acceptMinute.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        return result;
    }

}
