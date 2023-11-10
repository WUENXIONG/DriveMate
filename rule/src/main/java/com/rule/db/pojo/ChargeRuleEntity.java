package com.rule.db.pojo;

import java.io.Serializable;
import java.util.Date;

public class ChargeRuleEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    private String code;
    private Byte status;
    private Long id;
    private String name;
    private Date createTime;
    private String rule;

    public String getCode() {
        return this.code;
    }

    public Byte getStatus() {
        return this.status;
    }

    public Long getId() {
        return this.id;
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

    public void setStatus(Byte status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ChargeRuleEntity(String code, Byte status, Long id, String name, Date createTime, String rule) {
        this.code = code;
        this.status = status;
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.rule = rule;
    }

    public ChargeRuleEntity() {
    }

    public String toString() {
        String var10000 = this.getCode();
        return "ChargeRuleEntity(code=" + var10000 + ", status=" + this.getStatus() + ", id=" + this.getId() + ", name=" + this.getName() + ", createTime=" + this.getCreateTime() + ", rule=" + this.getRule() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ChargeRuleEntity)) {
            return false;
        } else {
            ChargeRuleEntity other = (ChargeRuleEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
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
        return other instanceof ChargeRuleEntity;
    }

    public int hashCode() {
        int result = 1;
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
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
