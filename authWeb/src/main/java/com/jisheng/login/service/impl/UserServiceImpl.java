package com.jisheng.login.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jisheng.login.dao.UserDao;
import com.jisheng.login.entity.User;
import com.jisheng.login.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {
}
