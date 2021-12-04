package com.ivan.sunnyblog.base;

import com.ivan.sunnyblog.web.cache.RedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: jinghaoliang
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {
//
//    @Autowired
//    RedisUtil redisUtil;

    @Autowired
    RedisCache redisCache;
//    RedisTemplate redisTemplate;

    @Test
    public void redisUtileTest(){
        Map<String, Object> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);

//        redisCache.set("ivankey1", 2);
//        Object ivankey1 = redisCache.get("ivankey1");
//        System.out.println(ivankey1);

//        redisCache.hset("testhash", map);
//        Map<String, Object> testhash = redisCache.hgetAll("testhash");
//        System.out.println(testhash);

//        redisUtil.test();

//        redisTemplate.opsForHash().put("token", "token_123123123123", 12);

//        redisTemplate.opsForValue().set("token", "this is token");
//        redisTemplate.opsForHash()
    }
}
