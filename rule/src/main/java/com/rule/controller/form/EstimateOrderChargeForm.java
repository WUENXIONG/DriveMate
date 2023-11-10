package com.rule.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Schema(description = "预估代驾费用的表单")
public class EstimateOrderChargeForm {
    @Schema(description = "代驾公里数")
    @NotBlank(message = "mileage不能为空")
    @Pattern(regexp = "^[1-9]\\d*\\.\\d+$|^0\\.\\d*[1-9]\\d*$|^[1-9]\\d*$", message = "mileage内容不正确")
    private String mileage;

    @Schema(description = "代驾开始时间")
    @NotBlank(message = "time不能为空")
    @Pattern(regexp = "^(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$", message = "time内容不正确")
    private String time;

    public String getMileage() {
        return this.mileage;
    }

    public String getTime() {
        return this.time;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public EstimateOrderChargeForm(String mileage, String time) {
        this.mileage = mileage;
        this.time = time;
    }

    public EstimateOrderChargeForm() {
    }

    public String toString() {
        String var10000 = this.getMileage();
        return "EstimateOrderChargeForm(mileage=" + var10000 + ", time=" + this.getTime() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof EstimateOrderChargeForm)) {
            return false;
        } else {
            EstimateOrderChargeForm other = (EstimateOrderChargeForm)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$mileage = this.getMileage();
                Object other$mileage = other.getMileage();
                if (this$mileage == null) {
                    if (other$mileage != null) {
                        return false;
                    }
                } else if (!this$mileage.equals(other$mileage)) {
                    return false;
                }

                Object this$time = this.getTime();
                Object other$time = other.getTime();
                if (this$time == null) {
                    if (other$time != null) {
                        return false;
                    }
                } else if (!this$time.equals(other$time)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof EstimateOrderChargeForm;
    }

    public int hashCode() {
        int result = 1;
        Object $mileage = this.getMileage();
        result = result * 59 + ($mileage == null ? 43 : $mileage.hashCode());
        Object $time = this.getTime();
        result = result * 59 + ($time == null ? 43 : $time.hashCode());
        return result;
    }

}
