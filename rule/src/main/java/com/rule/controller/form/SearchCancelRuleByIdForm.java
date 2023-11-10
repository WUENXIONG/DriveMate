package com.rule.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Schema(description = "根据ID查询取消规则的表单")
public class SearchCancelRuleByIdForm {
    @Schema(description = "规则ID")
    @NotNull(message = "ruleId不为空")
    @Min(value = 1L, message = "ruleId不能小于1")
    private Long ruleId;

    public Long getRuleId() {
        return this.ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public SearchCancelRuleByIdForm(Long ruleId) {
        this.ruleId = ruleId;
    }

    public SearchCancelRuleByIdForm() {
    }

    public String toString() {
        return "SearchCancelRuleByIdForm(ruleId=" + this.getRuleId() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SearchCancelRuleByIdForm)) {
            return false;
        } else {
            SearchCancelRuleByIdForm other = (SearchCancelRuleByIdForm)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$ruleId = this.getRuleId();
                Object other$ruleId = other.getRuleId();
                if (this$ruleId == null) {
                    if (other$ruleId != null) {
                        return false;
                    }
                } else if (!this$ruleId.equals(other$ruleId)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof SearchCancelRuleByIdForm;
    }

    public int hashCode() {
        int result = 1;
        Object $ruleId = this.getRuleId();
        result = result * 59 + ($ruleId == null ? 43 : $ruleId.hashCode());
        return result;
    }
}
