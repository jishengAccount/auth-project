package com.jisheng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jisheng.dao.UserDao;
import com.jisheng.entity.User;
import com.jisheng.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {
}
