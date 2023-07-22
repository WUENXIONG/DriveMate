package com.common.config.xss;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.json.JSONUtil;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;


/*防止Xss攻击核心是将Html格式的内容转成文本，并重写所有能拿到参数的方法:
  getParameter
  getParameterValues
  getParameterMap
  getInputStream
* 使用java工具库hutool提供的HtmlUtil的cleanHtmlTag去除Html的标签*/

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name){
        String value = super.getParameter(name);
        if(!StrUtil.hasEmpty(value))
            value = HtmlUtil.cleanHtmlTag(value);
        return value;
    }

    @Override
    public String[] getParameterValues(String name){
        String[] values = super.getParameterValues(name);
        if(values != null) {
            for (int i = 0; i < values.length; ++i) {
                if (!StrUtil.hasEmpty(values[i]))
                    values[i] = HtmlUtil.cleanHtmlTag(values[i]);
            }
        }
        return values;
    }

    @Override
    public Map<String,String[]> getParameterMap(){
        Map<String, String[]> parameters = super.getParameterMap();
        LinkedHashMap<String, String[]> map = new LinkedHashMap<>();
        if (parameters != null){
            for(String key:parameters.keySet()){
                String[] values = parameters.get(key);
                for(int i = 0; i < values.length; ++i) {
                    if (!StrUtil.hasEmpty(values[i])) {
                        values[i] = HtmlUtil.cleanHtmlTag(values[i]);
                    }
                }
                map.put(key,values);
            }
        }
        return map;
    }

    @Override
    public String getHeader(String name){
        String value = super.getHeader(name);
        if(!StrUtil.hasEmpty(value))
            value = HtmlUtil.cleanHtmlTag(value);

        return value;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        InputStream in = super.getInputStream();
        InputStreamReader reader = new InputStreamReader(in, Charset.forName("UTF-8"));
        BufferedReader buffer = new BufferedReader(reader);
        StringBuffer body = new StringBuffer();
        String line = buffer.readLine();
        while(line != null){
            body.append(line);
            line = buffer.readLine();
        }
        buffer.close();
        reader.close();
        in.close();
        Map<String, Object> map = JSONUtil.parseObj(body.toString());
        Map<>

    }
}
