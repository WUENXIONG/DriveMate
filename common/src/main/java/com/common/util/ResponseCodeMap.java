package com.common.util;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ResponseCodeMap extends HashMap<String, Object> {
    public ResponseCodeMap(){
        put("code", HttpStatus.SC_OK);
        put("msg","success");
    }

    @Override
    public ResponseCodeMap put(String key, Object value){
        super.put(key,value);
        return this;
    }

    public static ResponseCodeMap ok(){
        return new ResponseCodeMap();
    }

    public static ResponseCodeMap ok(String msg){
        ResponseCodeMap respMap = new ResponseCodeMap();
        respMap.put("msg",msg);
        return respMap;
    }

    public static ResponseCodeMap ok(Map<String, Object> map){
        ResponseCodeMap respMap = new ResponseCodeMap();
        respMap.putAll(map);
        return respMap;
    }

    public static ResponseCodeMap error(int code, String msg){
        ResponseCodeMap respMap = new ResponseCodeMap();
        respMap.put("code",code);
        respMap.put("msg",msg);
        return respMap;
    }

    public static ResponseCodeMap error(String msg){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static ResponseCodeMap error(){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知错误");
    }
}
