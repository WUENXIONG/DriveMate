package com.mis.service.impl;

import com.common.exception.DriveMateException;
import com.common.util.DataPaging;
import com.mis.db.dao.RoleDao;
import com.mis.db.pojo.RoleEntity;
import com.mis.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public ArrayList<HashMap> searchAllRole() {
        ArrayList<HashMap> list = roleDao.searchAllRole();
        return list;
    }

    @Override
    public HashMap searchById(int id) {
        HashMap map = roleDao.searchById(id);
//        String defaultPermissions = MapUtil.getStr(map, "defaultPermissions");
//        String permissions=MapUtil.getStr(map,"permissions");
//        map.replace("defaultPermissions", JSONUtil.parseArray("defaultPermissions"));
//        map.replace("permissions",JSONUtil.parseArray("permissions"));
        return map;
    }

    @Override
    public DataPaging searchRoleByPage(HashMap param) {
        ArrayList<HashMap> list = roleDao.searchRoleByPage(param);
        long count = roleDao.searchRoleCount(param);
        int start = (Integer) param.get("start");
        int length = (Integer) param.get("length");
        DataPaging pageDef = new DataPaging(list, count, start, length);
        return pageDef;
    }

    @Override
    public int insert(RoleEntity role) {
        int rows = roleDao.insert(role);
        return rows;
    }

    @Override
    public ArrayList<Integer> searchUserIdByRoleId(int roleId) {
        ArrayList<Integer> list = roleDao.searchUserIdByRoleId(roleId);
        return list;
    }

    @Override
    public int update(RoleEntity role) {
        int rows = roleDao.update(role);
        return rows;
    }

    @Override
    public int deleteRoleByIds(Integer[] ids) {
        if (!roleDao.searchCanDelete(ids)) {
            throw new DriveMateException("无法删除关联用户的角色");
        }
        int rows = roleDao.deleteRoleByIds(ids);
        return rows;
    }
}
