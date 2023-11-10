package com.rule.config;

import cn.hutool.json.JSONObject;
import com.common.exception.DriveMateException;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    public ExceptionAdvice() {
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public String exceptionHandler(Exception e) {
        JSONObject jsonObject = new JSONObject();
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException var3 = (MethodArgumentNotValidException)e;
            jsonObject.set("error", var3.getBindingResult().getFieldError().getDefaultMessage());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            jsonObject.set("error", "Web方法不支持当前的请求类型");
        } else if (e instanceof HttpMessageNotReadableException) {
            jsonObject.set("error", "缺少提交的数据");
        } else if (e instanceof DriveMateException) {
            log.error("执行异常", e);
            DriveMateException var4 = (DriveMateException)e;
            jsonObject.set("error", var4.getMsg());
        } else {
            log.error("执行异常", e);
            jsonObject.set("error", "执行异常");
        }

        return jsonObject.toString();
    }
}
