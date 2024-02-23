package com.bff_customer.feign;

import com.bff_customer.controller.form.UseVoucherForm;
import com.common.util.ResponseCodeMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "voucher")
public interface VoucherServiceAPI {

    @PostMapping("/voucher/customer/useVoucher")
    public ResponseCodeMap useVoucher(UseVoucherForm form);


}
