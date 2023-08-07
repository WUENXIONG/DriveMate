package com.map.exception;

import cn.hutool.json.JSONObject;
import com.common.exception.DriveMateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        JSONObject json = new JSONObject();

        if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException)e;
            json.set("error",exception.getBindingResult().getFieldError().getDefaultMessage());
        }

        else if(e instanceof HttpRequestMethodNotSupportedException){
            json.set("error","Web方法不支持当前请求类型");
        }

        else if(e instanceof HttpMessageNotReadableException){
            json.set("error","缺少需提交的数据");
        }

        else if(e instanceof DriveMateException){
            log.error("执行异常",e);
            DriveMateException exception = (DriveMateException) e;
            json.set("error" , exception.getMsg());
        }

        else{
            log.error("其它异常",e);
            json.set("error","其它异常");
        }
        return json.toString();
    }
}

