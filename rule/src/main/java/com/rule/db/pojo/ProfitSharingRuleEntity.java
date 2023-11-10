package com.rule.db.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProfitSharingRuleEntity implements Serializable {
    private String code;
    private Long id;
    private Integer status;
    private String name;
    private Date createTime;
    private static final long serialVersionUID = 1L;
    private String rule;

    public String getCode() {
        return this.code;
    }

    public Long getId() {
        return this.id;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getName() {
        return this.name;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getRule() {
        return this.rule;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public ProfitSharingRuleEntity(String code, Long id, Integer status, String name, Date createTime, String rule) {
        this.code = code;
        this.id = id;
        this.status = status;
        this.name = name;
        this.createTime = createTime;
        this.rule = rule;
    }

    public ProfitSharingRuleEntity() {
    }

    public String toString() {
        String var10000 = this.getCode();
        return "ProfitsharingRuleEntity(code=" + var10000 + ", id=" + this.getId() + ", status=" + this.getStatus() + ", name=" + this.getName() + ", createTime=" + this.getCreateTime() + ", rule=" + this.getRule() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ProfitSharingRuleEntity)) {
            return false;
        } else {
            ProfitSharingRuleEntity other = (ProfitSharingRuleEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
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

                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
                    return false;
                }

                label62: {
                    Object this$name = this.getName();
                    Object other$name = other.getName();
                    if (this$name == null) {
                        if (other$name == null) {
                            break label62;
                        }
                    } else if (this$name.equals(other$name)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$createTime = this.getCreateTime();
                    Object other$createTime = other.getCreateTime();
                    if (this$createTime == null) {
                        if (other$createTime == null) {
                            break label55;
                        }
                    } else if (this$createTime.equals(other$createTime)) {
                        break label55;
                    }

                    return false;
                }

                Object this$rule = this.getRule();
                Object other$rule = other.getRule();
                if (this$rule == null) {
                    if (other$rule != null) {
                        return false;
                    }
                } else if (!this$rule.equals(other$rule)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProfitSharingRuleEntity;
    }

    public int hashCode() {
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $rule = this.getRule();
        result = result * 59 + ($rule == null ? 43 : $rule.hashCode());
        return result;
    }
}
