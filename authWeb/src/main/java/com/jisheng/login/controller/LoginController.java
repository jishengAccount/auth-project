package com.jisheng.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jisheng.common.entity.resultCommon.Result;
import com.jisheng.common.entity.resultCommon.ResultCode;
import com.jisheng.common.jwt.JwtUtils;
import com.jisheng.login.entity.User;
import com.jisheng.login.service.LoginService;
import com.jisheng.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
//@RequestMapping("/sys")
@Transactional
public class LoginController {
    @Autowired
    public LoginService loginService;
    @Autowired
    public JwtUtils jwtUtils;
    @Autowired
    public UserService userService;

    @RequestMapping(value = "/webSave",method = RequestMethod.GET)
    public Object webSave() throws Exception{

//        List<User> byId = userService.list();
        //根据id查询
//        User byId = userService.getById("1075383135459430400");
        //条件查询
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id","1063705482939731968");
        User one = userService.getOne(userQueryWrapper);
        //分页查询
        IPage<User> userIPage = new Page<User>(0,3);
        IPage<User> page = userService.page(userIPage);
        //更新
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id","1063705482939731968");
        User user = new User();
        user.setMobile("46456452342346");
        boolean flag = userService.update(user, userUpdateWrapper);
        //保存
        User user1 = new User();
        user1.setMobile("13555555");
        user1.setUsername("jay");
        user1.setCompanyName("淘宝");
        userService.save(user1);
        return new Result(ResultCode.SUCCESS,page);
    }

    //jwt登录
//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    public Result login(){
//        String phone="13800000001";
//        String password="88012a09484c94fcec9e65b2377c44b9";
//        User byPhone = loginService.findByPhone(phone);
//        String token="";
//        if (byPhone!=null&&byPhone.getPassword().equals(password)){
//            Map map = new HashMap<>();
//            map.put("phone",byPhone.getMobile());
//            token = jwtUtils.CreateClaims(byPhone.getId(), byPhone.getUsername(), map);
//        }
//        return new Result(ResultCode.SUCCESS,token);
//    }

    //登录成功获取权限
//    @RequestMapping(value = "/getAuth",method = RequestMethod.GET)
//    public Result getAuth(HttpServletRequest request){
//        //从jwt拦截器放入到request域中获取claim
//        Claims claim = (Claims)request.getAttribute("claim");
//        User byId = loginService.findById(1063705482939731968l);
//        ProfileResult profileResult = new ProfileResult(byId);
//        //获取用户权限
//        return new Result(ResultCode.SUCCESS,profileResult);
//    }

//    @RequestMapping(value = "/login2",method = RequestMethod.GET)
//    public Result loginByShiro(){
//        String phone="13800000001";
//        String password="123456";
//        try {
//            Md5Hash md5Hash = new Md5Hash(password, phone, 3);
//            Subject subject = SecurityUtils.getSubject();
//            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(phone, md5Hash.toString());
//            subject.login(usernamePasswordToken);//跳转到自定义realm的doAuthenticator()进行验证
//            //获取shiro中SessionManager中的sessionId
//            String id = subject.getSession().getId().toString();
//            return new Result(ResultCode.SUCCESS,id);
//        }catch (Exception e){
//            return  new Result(ResultCode.FAIL);
//        }
//    }


}
