package com.mis.service;

import com.common.util.DataPaging;
import com.mis.db.pojo.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface UserService {
//    public HashMap createQrCode();

//    public boolean checkQrCode(String code, String uuid);

    public HashMap wechatLogin(String uuid);

    public Set<String> searchUserPermissions(int userId);

    public HashMap searchById(int userId);

    public HashMap searchUserSummary(int userId);

    public ArrayList<HashMap> searchAllUser();

    public Integer login(Map param);

    public int updatePassword(Map param);

    public DataPaging searchUserByPage(Map param);

    public int insert(UserEntity user);

    public int update(Map param);

    public int deleteUserByIds(Integer[] ids);

    public ArrayList<String> searchUserRoles(int userId);

    public HashMap searchNameAndDept(int userId);
}
