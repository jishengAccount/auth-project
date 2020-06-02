package com.jisheng.login.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jisheng.login.dao.PermissionDao;
import com.jisheng.login.dao.RoleDao;
import com.jisheng.login.dao.UserDao;
import com.jisheng.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional
public class LoginService {
    @Autowired
    private UserDao userDao;
    @Autowired
    PermissionDao permissionDao;
    @Autowired
    RoleDao roleDao;
    public  List<User> findById(String id){
        System.out.println(userDao);
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<User>();
        List<User> users = userDao.selectList(objectQueryWrapper);
        return users;
    }

//    public User findByPhone(String phone){
//        User byPhone = userDao.findByMobile(phone);
//        return byPhone;
//
//    }

//    public Set<Permission> getPermission(User user){
//        Role byUser = roleDao.findByUser(user);
//        return byUser.getPermissions();
//    }
}
