package com.ivan.sunnyblog;

//import org.junit.jupiter.api.Test;
import com.ivan.sunnyblog.base.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class SunnyBlogApplicationTests {

    @Autowired
    RedisUtil redisUtil;


    @Test
    void contextLoads() {
    }

    @Test
    public void redisUtileTest() {
//        BlogUser user = new BlogUser();
//        user.setNickname("hhhhhhhhhhhh");
                System.out.println(redisUtil);
        redisUtil.test();
    }



}
