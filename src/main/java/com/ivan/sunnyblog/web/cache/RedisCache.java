package com.ivan.sunnyblog.web.cache;

import com.ivan.sunnyblog.web.cache.iservice.IGlobalCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Author: jinghaoliang
 **/

@Component
public class RedisCache implements IGlobalCache {

    @Autowired
    RedisTemplate redisTemplate;



    @Override
    public Boolean expire(String key, Long time) {
        return redisTemplate.expire(key,time, TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void del(String... key) {
        if(key != null && key.length > 0){
            if(key.length == 1){
                redisTemplate.delete(key);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, long time) {
        if(time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);

        }else{
            this.set(key, value);
        }
    }

    @Override
    public void hset(String hash, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(hash, map);
    }

    @Override
    public void hset(String hash, Object hashKey, Object hashValue) {
        redisTemplate.opsForHash().put(hash, hashKey, hashValue);
    }

    @Override
    public void hsetWithExpire(String hash, Object hashKey, Object hashValue, Long seconds) {
        redisTemplate.opsForHash().put(hash, hashKey, hashValue);

        if(seconds > 0) {
            expire(hash, seconds);
        }
    }

    @Override
    public Object hget(String hash, Object hashKey) {
        return redisTemplate.opsForHash().get(hash, hashKey);
    }

    @Override
    public Map<Object, Object> hgetAll(String hash) {
        return redisTemplate.opsForHash().entries(hash);
    }

    @Override
    public void hIncr(String hash, Object hashKey, int increment) {
        Long incr = redisTemplate.opsForHash().increment(hash, hashKey, increment);
//        System.out.println(incr);
    }


    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
//        redisTemplate.opsForHash().delete()
    }

    @Override
    public void hDelete(String hash, Object... hashKeys) {
        redisTemplate.opsForHash().delete(hash, hashKeys);
    }

    @Override
    public RedisTemplate getRedisTemplate() {
        return null;
    }
}
