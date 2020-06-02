package com.jisheng.redis;

import com.alibaba.fastjson.JSON;
import com.jisheng.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Component
@RestController
public class Test {
    @Autowired
    RedisTemplate redisTemplate;

    private final static String prefix="test:js:";

    @RequestMapping("testString")
    public String testString() {
        User user1 = new User();
        user1.setUsername("小米");
        user1.setMobile("142342342342");
        user1.setCompanyName("miplay");
        redisTemplate.opsForValue().set(prefix+"user", JSON.toJSONString(user1));
        return "成功";
    }

    @RequestMapping("testList")
    public String testList() {
        User user1 = new User();
        user1.setUsername("小米");
        user1.setMobile("142342342342");
        user1.setCompanyName("miplay");
        User user2 = new User();
        user2.setUsername("小q");
        user2.setMobile("86786456456");
        user2.setCompanyName("qplay");
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        redisTemplate.opsForList().leftPush("users", JSON.toJSONString(users));
        redisTemplate.opsForValue().set("users1", JSON.toJSONString(users));
        return "成功";
    }

    @RequestMapping("testHash")
    public String testHash() {
        User user1 = new User();
        user1.setUsername("小米");
        user1.setMobile("142342342342");
        user1.setCompanyName("miplay");
        User user2 = new User();
        user2.setUsername("小q");
        user2.setMobile("86786456456");
        user2.setCompanyName("qplay");
        BoundHashOperations hashOperations = redisTemplate.boundHashOps("hash结构");
        hashOperations.put("user1", JSON.toJSONString(user1));
        hashOperations.put("user2", JSON.toJSONString(user2));
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        redisTemplate.opsForHash().put("hash结构", "users", JSON.toJSONString(users));
        return "成功";
    }

    @RequestMapping("getHash")
    public Object getHash() {
        Object o1 = redisTemplate.opsForHash().get("hash结构", "users");
        List<User> o = JSON.parseArray((String) o1, User.class);
        for (User user : o) {
            System.out.println(user);
        }
        return o;
    }

    @RequestMapping("getString")
    public Object getString() {
        Object users1 = redisTemplate.opsForValue().get("users1");
        List<User> users = JSON.parseArray((String) users1, User.class);
        for (User u : users) {
            System.out.println(u);
        }

        return users;
    }

    @RequestMapping("getSet")
    public Object getSet() {

        return null;
    }
@RequestMapping("getList")
    public Object getList() {
    Object users = redisTemplate.opsForList().leftPop("users");
    List<User> userss = JSON.parseArray((String) users, User.class);
    for (User u : userss) {
        System.out.println(u);
    }
    return userss;
    }
}
