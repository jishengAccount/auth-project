package com.jisheng.login.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jisheng.login.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {

//       public User findByMobile(String mobile);

}
