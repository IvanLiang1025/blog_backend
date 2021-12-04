package com.ivan.sunnyblog.web.services;

import com.ivan.sunnyblog.base.models.tables.pojos.BlogUser;
import com.ivan.sunnyblog.base.utils.JwtUtil;
import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.cache.RedisCache;
import com.ivan.sunnyblog.web.cache.RedisKeyGenerator;
import com.ivan.sunnyblog.web.cache.iservice.IGlobalCache;
import com.ivan.sunnyblog.web.dao.BaseDao;
import com.ivan.sunnyblog.web.services.iservice.IBlogUserService;
import com.ivan.sunnyblog.web.vo.LoginResultVo;
import com.ivan.sunnyblog.web.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ivan.sunnyblog.base.models.tables.BlogUser.BLOG_USER;


/**
 * Author: jinghaoliang
 * Date: 2021-09-29 10:59 a.m.
 **/
@Service
public class BlogUserService extends BaseService implements IBlogUserService {

    @Autowired
    private BaseDao dao;

    @Autowired
    IGlobalCache redisCache;


    @Override
    public ResultInfo login(LoginVo loginVo){
        if(StringUtils.isBlank(loginVo.getUsername())){
            emptyPara("username");
        }
        if(StringUtils.isBlank(loginVo.getPassword())){
            emptyPara("password");
        }

        BlogUser blogUser = checkUsernameAndPwd(loginVo);

        if(blogUser == null) {
            bizError("error.user.login.username.invalid");
        }

        blogUser.setPassword("");

        String token = JwtUtil.createToken(blogUser.getUserId());

        redisCache.hsetWithExpire(RedisKeyGenerator.CACHE_TOKEN, token, blogUser.getUserId(), RedisKeyGenerator.EXPIRE_CACHE_TOKEN_SECONDS );

        LoginResultVo loginResultVo = new LoginResultVo();
        loginResultVo.setAccessToken(token);
        loginResultVo.setRole(blogUser.getRole());

        ResultInfo result = ResultInfo.getSucResult(loginResultVo);
        return result;
    }

    public BlogUser checkUsernameAndPwd(LoginVo loginVo){

        BlogUser blogUser = dao.getDslContext().select().from(BLOG_USER).where(BLOG_USER.USERNAME.eq(loginVo.getUsername()))
                .and(BLOG_USER.PASSWORD.eq(loginVo.getPassword())).fetchOneInto(BlogUser.class);
        if(blogUser == null) {
            return null;
        }



//        BlogUserRecord blogUserRecord = dao.getDslContext().newRecord(BLOG_USER);
//        blogUserRecord.setUsername("test");
//        blogUserRecord.setPassword("123456");
//        blogUserRecord.setEmail("277517929@qq.com");
//        blogUserRecord.setNickname("honey");
//        blogUserRecord.setStatus((short)1);
////        blogUserRecord.setCreateDate();
//
//        blogUserRecord.insert();

        return blogUser;

//
    }

    @Override
    public BlogUser findUserById(Long id) {
        BlogUser blogUser = dao.getDslContext().select().from(BLOG_USER).where(BLOG_USER.USER_ID.eq(id)).fetchOneInto(BlogUser.class);
        return blogUser;
    }
}
