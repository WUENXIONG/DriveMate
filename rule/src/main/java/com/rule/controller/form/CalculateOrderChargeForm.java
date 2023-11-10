package com.rule.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(description = "计算代驾费用的表单")
public class CalculateOrderChargeForm {
    @Schema(description = "等时分钟")
    @NotNull(message = "minute不能为空")
    @Min(value = 1L, message = "minute不能小于1")
    private Integer minute;

    @Schema(description = "代驾开始时间")
    @NotBlank(message = "time不能为空")
    @Pattern(regexp = "^(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$", message = "time内容不正确")
    private String time;

    @Schema(description = "代驾公里数")
    @NotBlank(message = "mileage不能为空")
    @Pattern(regexp = "^[1-9]\\d*\\.\\d+$|^0\\.\\d+$|^[1-9]\\d*$", message = "mileage内容不正确")
    private String mileage;

    public Integer getMinute() {
        return this.minute;
    }

    public String getTime() {
        return this.time;
    }

    public String getMileage() {
        return this.mileage;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public CalculateOrderChargeForm(Integer minute, String time, String mileage) {
        this.minute = minute;
        this.time = time;
        this.mileage = mileage;
    }

    public CalculateOrderChargeForm() {
    }

    public String toString() {
        Integer var10000 = this.getMinute();
        return "CalculateOrderChargeForm(minute=" + var10000 + ", time=" + this.getTime() + ", mileage=" + this.getMileage() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CalculateOrderChargeForm)) {
            return false;
        } else {
            CalculateOrderChargeForm other = (CalculateOrderChargeForm)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$minute = this.getMinute();
                    Object other$minute = other.getMinute();
                    if (this$minute == null) {
                        if (other$minute == null) {
                            break label47;
                        }
                    } else if (this$minute.equals(other$minute)) {
                        break label47;
                    }

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

                Object this$mileage = this.getMileage();
                Object other$mileage = other.getMileage();
                if (this$mileage == null) {
                    if (other$mileage != null) {
                        return false;
                    }
                } else if (!this$mileage.equals(other$mileage)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof CalculateOrderChargeForm;
    }

    public int hashCode() {
        int result = 1;
        Object $minute = this.getMinute();
        result = result * 59 + ($minute == null ? 43 : $minute.hashCode());
        Object $time = this.getTime();
        result = result * 59 + ($time == null ? 43 : $time.hashCode());
        Object $mileage = this.getMileage();
        result = result * 59 + ($mileage == null ? 43 : $mileage.hashCode());
        return result;
    }

}
