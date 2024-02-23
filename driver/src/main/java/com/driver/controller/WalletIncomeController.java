package com.driver.controller;

import cn.hutool.core.bean.BeanUtil;
import com.common.util.ResponseCodeMap;
import com.driver.controller.form.TransferForm;
import com.driver.db.pojo.WalletIncomeEntity;
import com.driver.service.WalletIncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/wallet/income")
@Tag(name = "WalletIncomeController", description = "司机钱包入账Web接口")
public class WalletIncomeController {

    @Resource
    private WalletIncomeService incomeService;

    @PostMapping("/transfer")
    @Operation(summary = "转账")
    public ResponseCodeMap transfer(@RequestBody @Valid TransferForm form) {
        WalletIncomeEntity entity = BeanUtil.toBean(form, WalletIncomeEntity.class);
        entity.setStatus((byte) 3);
        int rows = incomeService.transfer(entity);
        return ResponseCodeMap.ok().put("rows", rows);
    }
}

