package com.bff_customer.config;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
    *司机端只有司机一种角色，所以不需要判断用户角色和权限
    *只要登录了就行。但是stp框架必须实现这个接口，都返回空就行
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    //返回用户权限集合
    @Override
    public List<String> getPermissionList(Object o, String s) {
        return new ArrayList<String>();
    }

    //返回角色标识集合
    @Override
    public List<String> getRoleList(Object o, String s) {
        return new ArrayList<String>();
    }
}
