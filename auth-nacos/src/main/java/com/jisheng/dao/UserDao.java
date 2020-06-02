package com.jisheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jisheng.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {

//       public User findByMobile(String mobile);

}
