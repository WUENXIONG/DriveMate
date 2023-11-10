package com.rule.db.pojo;

import java.io.Serializable;
import java.util.Date;

public class CancelRuleEntity implements Serializable{
    private String rule;
    private String code;
    private String name;
    private Date createTime;
    private Byte type;
    private static final long serialVersionUID = 1L;
    private Byte status;
    private Long id;

    public String getRule() {
        return this.rule;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Byte getType() {
        return this.type;
    }

    public Byte getStatus() {
        return this.status;
    }

    public Long getId() {
        return this.id;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CancelRuleEntity(String rule, String code, String name, Date createTime, Byte type, Byte status, Long id) {
        this.rule = rule;
        this.code = code;
        this.name = name;
        this.createTime = createTime;
        this.type = type;
        this.status = status;
        this.id = id;
    }

    public CancelRuleEntity() {
    }

    public String toString() {
        String var10000 = this.getRule();
        return "CancelRuleEntity(rule=" + var10000 + ", code=" + this.getCode() + ", name=" + this.getName() + ", createTime=" + this.getCreateTime() + ", type=" + this.getType() + ", status=" + this.getStatus() + ", id=" + this.getId() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CancelRuleEntity)) {
            return false;
        } else {
            CancelRuleEntity other = (CancelRuleEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label95: {
                    Object this$type = this.getType();
                    Object other$type = other.getType();
                    if (this$type == null) {
                        if (other$type == null) {
                            break label95;
                        }
                    } else if (this$type.equals(other$type)) {
                        break label95;
                    }

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

                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                label74: {
                    Object this$rule = this.getRule();
                    Object other$rule = other.getRule();
                    if (this$rule == null) {
                        if (other$rule == null) {
                            break label74;
                        }
                    } else if (this$rule.equals(other$rule)) {
                        break label74;
                    }

                    return false;
                }

                label67: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label67;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label67;
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

                Object this$createTime = this.getCreateTime();
                Object other$createTime = other.getCreateTime();
                if (this$createTime == null) {
                    if (other$createTime != null) {
                        return false;
                    }
                } else if (!this$createTime.equals(other$createTime)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof CancelRuleEntity;
    }

    public int hashCode() {
        int result = 1;
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $rule = this.getRule();
        result = result * 59 + ($rule == null ? 43 : $rule.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        return result;
    }
}
