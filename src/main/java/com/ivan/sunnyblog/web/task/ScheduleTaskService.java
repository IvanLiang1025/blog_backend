package com.ivan.sunnyblog.web.task;

import com.ivan.sunnyblog.web.cache.RedisKeyGenerator;
import com.ivan.sunnyblog.web.cache.iservice.IGlobalCache;
import com.ivan.sunnyblog.web.services.BaseService;
import com.ivan.sunnyblog.web.services.iservice.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Author: jinghaoliang
 **/

@Component
public class ScheduleTaskService extends BaseService {

    @Autowired
    IGlobalCache RedisCache;

    @Autowired
    IArticleService ArticleService;


    @Scheduled(cron = "0 5 15 * * ?")
    public void scheduledTask(){
        logger.info("===***=== Start to do scheduled task, date: {}", new Date());

        Map<Object, Object> blogViewMap = RedisCache.hgetAll(RedisKeyGenerator.CACHE_BLOG_VIEW_COUNT);
        blogViewMap.forEach((key, value) -> {

            Integer count = (Integer) value;
            if(count > 0) {
                Long id = RedisKeyGenerator.parseBlogViewMapKey((String) key);
                try{
                    ArticleService.updateViewCount(id, count);
                    RedisCache.hDelete(RedisKeyGenerator.CACHE_BLOG_VIEW_COUNT, RedisKeyGenerator.generateBlogViewMapKey(id));
                }catch (Exception e){
                    logger.error("Exception happened. failed to update view of article (id = {}). msg: {}", id, e.getMessage());
                }
            }
        });
        logger.info("===***=== The scheduled task is done, date: {}", new Date());
    }


}
