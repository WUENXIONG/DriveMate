package com.mis.config;

import cn.dev33.satoken.stp.StpInterface;
import com.mis.db.dao.UserDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static cn.dev33.satoken.sso.SaSsoConsts.ParamName.loginId;

/**
    *司机端只有司机一种角色，所以不需要判断用户角色和权限
    *只要登录了就行。但是stp框架必须实现这个接口，都返回空就行
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UserDao userDao;
    //返回用户权限集合
    @Override
    public List<String> getPermissionList(Object o, String s) {
        int userId = Integer.parseInt(loginId.toString());

        Set<String> permissions = userDao.searchUserPermissions(userId);
        ArrayList list = new ArrayList();
        list.add(permissions);
        return list;
    }

    //返回角色标识集合
    @Override
    public List<String> getRoleList(Object o, String s) {
        return new ArrayList<String>();
    }
}
