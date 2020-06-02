package com.jisheng.test.controller;

import com.jisheng.common.entity.resultCommon.Result;
import com.jisheng.common.entity.resultCommon.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
public class ProviderController {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 测试feign远程调用
     *
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "hello";
    }

    /**
     * 测试redis缓存，分布式锁
     *
     * @return
     */
    @RequestMapping("/queryRedis")
    public synchronized Result queryRedis() {
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "123");//setnx
        if (lock) {
            String value = (String) redisTemplate.opsForValue().get("num");
            if (StringUtils.isEmpty(value)) {
                //查询数据库
            }
            int num = Integer.parseInt(value);
            redisTemplate.opsForValue().set("num", String.valueOf(++num));
            //释放锁
            redisTemplate.delete("lock");
        }else{
            //尝试重新获取锁
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queryRedis();
        }
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 改进1
     * 问题：setnx刚好获取到锁，业务逻辑出现异常，导致锁无法释放
       解决：设置过期时间，自动释放锁。
     */
    public synchronized Result queryRedis1() {
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "123",3, TimeUnit.SECONDS);//setnx
        if (lock) {
            String value = (String) redisTemplate.opsForValue().get("num");
            if (StringUtils.isEmpty(value)) {
                //查询数据库
            }
            int num = Integer.parseInt(value);
            redisTemplate.opsForValue().set("num", String.valueOf(++num));
            //释放锁
            redisTemplate.delete("lock");
        }else{
            //尝试重新获取锁
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queryRedis();
        }
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 改进2
     *  问题：可能会释放其他服务器的锁。
         场景：如果业务逻辑的执行时间是7s。执行流程如下
         1. index1业务逻辑没执行完，3秒后锁被自动释放。
         2. index2获取到锁，执行业务逻辑，3秒后锁被自动释放。
         3. index3获取到锁，执行业务逻辑
         4. index1业务逻辑执行完成，开始调用del释放锁，这时释放的是index3的锁，导致index3的业务只执行1s就被别人释放。
         最终等于没锁的情况。
         解决：setnx获取锁时，设置一个指定的唯一值（例如：uuid）；释放前获取这个值，判断是否自己的锁
     */
    public synchronized Result queryRedis2() {
        String uuid = UUID.randomUUID().toString();
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid,3, TimeUnit.SECONDS);//setnx
        if (lock) {
            String value = (String) redisTemplate.opsForValue().get("num");
            if (StringUtils.isEmpty(value)) {
                //查询数据库
            }
            int num = Integer.parseInt(value);
            redisTemplate.opsForValue().set("num", String.valueOf(++num));
            //释放锁
            if (redisTemplate.opsForValue().get("lock").equals(uuid)){
                redisTemplate.delete("lock");
            }
        }else{
            //尝试重新获取锁
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queryRedis();
        }
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 问题：删除操作缺乏原子性。
         场景：
         1. index1执行删除时，查询到的lock值确实和uuid相等
         2. index1执行删除前，lock刚好过期时间已到，被redis自动释放
         3. index2获取了lock
         4. index1执行删除，此时会把index2的lock删除
        解决：使用luna脚本
     * @return
     */
    public synchronized Result queryRedis3() {
        String uuid = UUID.randomUUID().toString();
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid,3, TimeUnit.SECONDS);//setnx
        if (lock) {
            String value = (String) redisTemplate.opsForValue().get("num");
            if (StringUtils.isEmpty(value)) {
                //查询数据库
            }
            int num = Integer.parseInt(value);
            redisTemplate.opsForValue().set("num", String.valueOf(++num));
            //释放锁
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            this.redisTemplate.execute(new DefaultRedisScript<>(script), Arrays.asList("lock"), Arrays.asList(uuid));
        }else{
            //尝试重新获取锁
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queryRedis();
        }
        return new Result(ResultCode.SUCCESS);
    }

    //使用redis定时关闭订单



}
