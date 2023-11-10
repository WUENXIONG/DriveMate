package com.rule.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Schema(description = "根据ID查询分账规则的表单")
public class SearchProfitSharingRuleByIdForm {
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

    public SearchProfitSharingRuleByIdForm(Long ruleId) {
        this.ruleId = ruleId;
    }

    public SearchProfitSharingRuleByIdForm() {
    }

    public String toString() {
        return "SearchProfitsharingRuleByIdForm(ruleId=" + this.getRuleId() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SearchProfitSharingRuleByIdForm)) {
            return false;
        } else {
            SearchProfitSharingRuleByIdForm other = (SearchProfitSharingRuleByIdForm)o;
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
        return other instanceof SearchProfitSharingRuleByIdForm;
    }

    public int hashCode() {
        int result = 1;
        Object $ruleId = this.getRuleId();
        result = result * 59 + ($ruleId == null ? 43 : $ruleId.hashCode());
        return result;
    }

}
