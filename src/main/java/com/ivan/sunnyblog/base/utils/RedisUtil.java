package com.ivan.sunnyblog.base.utils;

import com.ivan.sunnyblog.base.models.tables.pojos.BlogUser;
import com.ivan.sunnyblog.web.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Author: jinghaoliang
 **/

@Component
public class RedisUtil {

    @Autowired
    RedisTemplate redisTemplate;

//    public RedisUtil() {
//
//    }

    public void test(){

        ResultInfo resultInfo = ResultInfo.getResult();



        redisTemplate.opsForValue().set("resultinfo", resultInfo);

        Object obj = redisTemplate.opsForValue().get("resultinfo");
        System.out.println(obj);

    }
}
