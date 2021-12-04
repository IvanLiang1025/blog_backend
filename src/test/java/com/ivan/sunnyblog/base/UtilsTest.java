package com.ivan.sunnyblog.base;

import com.ivan.sunnyblog.base.utils.JwtUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: jinghaoliang
 **/

public class UtilsTest {

    @Test
    public void testJwtUtil() {
        String token = JwtUtil.createToken(12l);
        System.out.println(token);
        Map<String, Object> stringObjectMap = JwtUtil.verifyToken(token);
        System.out.println(stringObjectMap.get("id"));



    }
}
