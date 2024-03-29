package com.mis.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.common.exception.DriveMateException;
import com.common.util.DataPaging;
import com.common.util.ResponseCodeMap;
import com.mis.controller.form.SearchOrderByPageForm;
import com.mis.controller.form.SearchOrderComprehensiveInfoForm;
import com.mis.controller.form.SearchOrderLastGpsForm;
import com.mis.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/order")
@Tag(name = "OrderController", description = "订单管理Web接口")
public class OrderController {

    @Resource
    OrderService orderService;

    @PostMapping("/searchOrderByPage")
    @SaCheckPermission(value = {"ROOT", "ORDER:SELECT"}, mode = SaMode.OR)
    @Operation(summary = "查询订单分页记录")
    public ResponseCodeMap searchOrderByPage(@RequestBody @Valid SearchOrderByPageForm form) {
        DataPaging dataPaging = orderService.searchOrderByPage(form);
        return ResponseCodeMap.ok().put("result", dataPaging);
    }

    @PostMapping("/searchOrderComprehensiveInfo")
    @SaCheckPermission(value = {"ROOT", "ORDER:SELECT"}, mode = SaMode.OR)
    @Operation(summary = "查询订单")
    public ResponseCodeMap searchOrderComprehensiveInfo(@RequestBody @Valid SearchOrderComprehensiveInfoForm form) {
        HashMap result = orderService.searchOrderComprehensiveInfo(form.getOrderId());
        return ResponseCodeMap.ok().put("result", result);
    }

    @PostMapping("/searchOrderLastGps")
    @SaCheckPermission(value = {"ROOT", "ORDER:SELECT"}, mode = SaMode.OR)
    @Operation(summary = "获取某个订单最后的GPS定位")
    public ResponseCodeMap searchOrderLastGps(@RequestBody @Valid SearchOrderLastGpsForm form) {
        HashMap result = orderService.searchOrderLastGps(form);
        return ResponseCodeMap.ok().put("result", result);
    }

    @GetMapping("/downloadOrderStartLocationIn30Days")
    @SaCheckPermission(value = {"ROOT", "ORDER:SELECT"}, mode = SaMode.OR)
    @Operation(summary = "查询30天内订单上车点定位记录")
    public void downloadOrderStartLocationIn30Days(HttpServletResponse response) {
        ArrayList<HashMap> result = orderService.searchOrderStartLocationIn30Days();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=heat_data.xls");
        try (ServletOutputStream out = response.getOutputStream();
             BufferedOutputStream bff = new BufferedOutputStream(out);) {
            ExcelWriter writer = ExcelUtil.getWriter();
            writer.write(result, true);
            writer.flush(bff);
            writer.close();
        } catch (Exception e) {
            throw new DriveMateException("Excel文件下载失败");
        }
    }

}

