package com.ivan.sunnyblog.web.cache;

/**
 * Author: jinghaoliang
 **/

public class RedisKeyGenerator {

    public static final Long EXPIRE_CACHE_TOKEN_SECONDS = 60 * 60 * 4L;

    public static final String CACHE_TOKEN = "tokenCache";

    public static final String CACHE_BLOG_VIEW_COUNT = "blogViewCountCache";
    public static final String CACHE_BLOG_VIEW_HK_PREFIX = "article_";

    public static String generateBlogViewMapKey (Long articleId) {
        return CACHE_BLOG_VIEW_HK_PREFIX + articleId.toString();
    }

    public static Long parseBlogViewMapKey (String key) {
        System.out.println(key);
        String id = key.substring(CACHE_BLOG_VIEW_HK_PREFIX.length());
        return Long.valueOf(id);
    }
}
