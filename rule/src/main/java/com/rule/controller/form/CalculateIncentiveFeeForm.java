package com.rule.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Schema(description = "计算系统奖励的表单")
public class CalculateIncentiveFeeForm {
    @Schema(description = "司机ID")
    @NotNull(message = "driverId不能为空")
    @Min(value = 1L, message = "driverId不能小于1")
    private long driverId;

    private String acceptTime;

    public long getDriverId() {
        return this.driverId;
    }

    public String getAcceptTime() {
        return this.acceptTime;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public CalculateIncentiveFeeForm(long driverId, String acceptTime) {
        this.driverId = driverId;
        this.acceptTime = acceptTime;
    }

    public CalculateIncentiveFeeForm() {
    }

    public String toString() {
        long var10000 = this.getDriverId();
        return "CalculateIncentiveFeeForm(driverId=" + var10000 + ", acceptTime=" + this.getAcceptTime() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CalculateIncentiveFeeForm)) {
            return false;
        } else {
            CalculateIncentiveFeeForm other = (CalculateIncentiveFeeForm)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getDriverId() != other.getDriverId()) {
                return false;
            } else {
                Object this$acceptTime = this.getAcceptTime();
                Object other$acceptTime = other.getAcceptTime();
                if (this$acceptTime == null) {
                    if (other$acceptTime != null) {
                        return false;
                    }
                } else if (!this$acceptTime.equals(other$acceptTime)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof CalculateIncentiveFeeForm;
    }

    public int hashCode() {
        int result = 1;
        long $driverId = this.getDriverId();
        result = result * 59 + (int)($driverId >>> 32 ^ $driverId);
        Object $acceptTime = this.getAcceptTime();
        result = result * 59 + ($acceptTime == null ? 43 : $acceptTime.hashCode());
        return result;
    }

}
