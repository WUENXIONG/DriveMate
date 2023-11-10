package com.rule.bean;

public class Voucher {
    private String remark = "订单取消系统补偿的代金券";
    private Integer usedCount = 0;
    private Integer type = 1;
    private String withAmount = "0";
    private String endTime;
    private Integer status = 1;
    private Integer timeType;
    private Integer days;
    private Integer limitQuota = 1;
    private String discount;
    private Integer takeCount = 1;
    private String tag = "补偿券";
    private String name;
    private String startTime;
    private Integer totalQuota = 1;

    public String getRemark() {
        return this.remark;
    }

    public Integer getUsedCount() {
        return this.usedCount;
    }

    public Integer getType() {
        return this.type;
    }

    public String getWithAmount() {
        return this.withAmount;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Integer getTimeType() {
        return this.timeType;
    }

    public Integer getDays() {
        return this.days;
    }

    public Integer getLimitQuota() {
        return this.limitQuota;
    }

    public String getDiscount() {
        return this.discount;
    }

    public Integer getTakeCount() {
        return this.takeCount;
    }

    public String getTag() {
        return this.tag;
    }

    public String getName() {
        return this.name;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public Integer getTotalQuota() {
        return this.totalQuota;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setWithAmount(String withAmount) {
        this.withAmount = withAmount;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public void setLimitQuota(Integer limitQuota) {
        this.limitQuota = limitQuota;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setTotalQuota(Integer totalQuota) {
        this.totalQuota = totalQuota;
    }

    public Voucher(String remark, Integer usedCount, Integer type, String withAmount, String endTime, Integer status, Integer timeType, Integer days, Integer limitQuota, String discount, Integer takeCount, String tag, String name, String startTime, Integer totalQuota) {
        this.remark = remark;
        this.usedCount = usedCount;
        this.type = type;
        this.withAmount = withAmount;
        this.endTime = endTime;
        this.status = status;
        this.timeType = timeType;
        this.days = days;
        this.limitQuota = limitQuota;
        this.discount = discount;
        this.takeCount = takeCount;
        this.tag = tag;
        this.name = name;
        this.startTime = startTime;
        this.totalQuota = totalQuota;
    }

    public Voucher() {
    }

    public String toString() {
        String var10000 = this.getRemark();
        return "Voucher(remark=" + var10000 + ", usedCount=" + this.getUsedCount() + ", type=" + this.getType() + ", withAmount=" + this.getWithAmount() + ", endTime=" + this.getEndTime() + ", status=" + this.getStatus() + ", timeType=" + this.getTimeType() + ", days=" + this.getDays() + ", limitQuota=" + this.getLimitQuota() + ", discount=" + this.getDiscount() + ", takeCount=" + this.getTakeCount() + ", tag=" + this.getTag() + ", name=" + this.getName() + ", startTime=" + this.getStartTime() + ", totalQuota=" + this.getTotalQuota() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Voucher)) {
            return false;
        } else {
            Voucher other = (Voucher)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label191: {
                    Object this$usedCount = this.getUsedCount();
                    Object other$usedCount = other.getUsedCount();
                    if (this$usedCount == null) {
                        if (other$usedCount == null) {
                            break label191;
                        }
                    } else if (this$usedCount.equals(other$usedCount)) {
                        break label191;
                    }

                    return false;
                }

                Object this$type = this.getType();
                Object other$type = other.getType();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
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

                label170: {
                    Object this$timeType = this.getTimeType();
                    Object other$timeType = other.getTimeType();
                    if (this$timeType == null) {
                        if (other$timeType == null) {
                            break label170;
                        }
                    } else if (this$timeType.equals(other$timeType)) {
                        break label170;
                    }

                    return false;
                }

                label163: {
                    Object this$days = this.getDays();
                    Object other$days = other.getDays();
                    if (this$days == null) {
                        if (other$days == null) {
                            break label163;
                        }
                    } else if (this$days.equals(other$days)) {
                        break label163;
                    }

                    return false;
                }

                Object this$limitQuota = this.getLimitQuota();
                Object other$limitQuota = other.getLimitQuota();
                if (this$limitQuota == null) {
                    if (other$limitQuota != null) {
                        return false;
                    }
                } else if (!this$limitQuota.equals(other$limitQuota)) {
                    return false;
                }

                Object this$takeCount = this.getTakeCount();
                Object other$takeCount = other.getTakeCount();
                if (this$takeCount == null) {
                    if (other$takeCount != null) {
                        return false;
                    }
                } else if (!this$takeCount.equals(other$takeCount)) {
                    return false;
                }

                label142: {
                    Object this$totalQuota = this.getTotalQuota();
                    Object other$totalQuota = other.getTotalQuota();
                    if (this$totalQuota == null) {
                        if (other$totalQuota == null) {
                            break label142;
                        }
                    } else if (this$totalQuota.equals(other$totalQuota)) {
                        break label142;
                    }

                    return false;
                }

                label135: {
                    Object this$remark = this.getRemark();
                    Object other$remark = other.getRemark();
                    if (this$remark == null) {
                        if (other$remark == null) {
                            break label135;
                        }
                    } else if (this$remark.equals(other$remark)) {
                        break label135;
                    }

                    return false;
                }

                Object this$withAmount = this.getWithAmount();
                Object other$withAmount = other.getWithAmount();
                if (this$withAmount == null) {
                    if (other$withAmount != null) {
                        return false;
                    }
                } else if (!this$withAmount.equals(other$withAmount)) {
                    return false;
                }

                label121: {
                    Object this$endTime = this.getEndTime();
                    Object other$endTime = other.getEndTime();
                    if (this$endTime == null) {
                        if (other$endTime == null) {
                            break label121;
                        }
                    } else if (this$endTime.equals(other$endTime)) {
                        break label121;
                    }

                    return false;
                }

                Object this$discount = this.getDiscount();
                Object other$discount = other.getDiscount();
                if (this$discount == null) {
                    if (other$discount != null) {
                        return false;
                    }
                } else if (!this$discount.equals(other$discount)) {
                    return false;
                }

                label107: {
                    Object this$tag = this.getTag();
                    Object other$tag = other.getTag();
                    if (this$tag == null) {
                        if (other$tag == null) {
                            break label107;
                        }
                    } else if (this$tag.equals(other$tag)) {
                        break label107;
                    }

                    return false;
                }

                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$startTime = this.getStartTime();
                Object other$startTime = other.getStartTime();
                if (this$startTime == null) {
                    if (other$startTime != null) {
                        return false;
                    }
                } else if (!this$startTime.equals(other$startTime)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Voucher;
    }

    public int hashCode() {
        int result = 1;
        Object $usedCount = this.getUsedCount();
        result = result * 59 + ($usedCount == null ? 43 : $usedCount.hashCode());
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $timeType = this.getTimeType();
        result = result * 59 + ($timeType == null ? 43 : $timeType.hashCode());
        Object $days = this.getDays();
        result = result * 59 + ($days == null ? 43 : $days.hashCode());
        Object $limitQuota = this.getLimitQuota();
        result = result * 59 + ($limitQuota == null ? 43 : $limitQuota.hashCode());
        Object $takeCount = this.getTakeCount();
        result = result * 59 + ($takeCount == null ? 43 : $takeCount.hashCode());
        Object $totalQuota = this.getTotalQuota();
        result = result * 59 + ($totalQuota == null ? 43 : $totalQuota.hashCode());
        Object $remark = this.getRemark();
        result = result * 59 + ($remark == null ? 43 : $remark.hashCode());
        Object $withAmount = this.getWithAmount();
        result = result * 59 + ($withAmount == null ? 43 : $withAmount.hashCode());
        Object $endTime = this.getEndTime();
        result = result * 59 + ($endTime == null ? 43 : $endTime.hashCode());
        Object $discount = this.getDiscount();
        result = result * 59 + ($discount == null ? 43 : $discount.hashCode());
        Object $tag = this.getTag();
        result = result * 59 + ($tag == null ? 43 : $tag.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $startTime = this.getStartTime();
        result = result * 59 + ($startTime == null ? 43 : $startTime.hashCode());
        return result;
    }

}

