package com.ivan.sunnyblog.web.services;

import com.ivan.sunnyblog.base.models.tables.pojos.BlogUser;
import com.ivan.sunnyblog.base.models.tables.pojos.Category;
import com.ivan.sunnyblog.base.models.tables.records.ArticleRecord;
import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.cache.RedisKeyGenerator;
import com.ivan.sunnyblog.web.cache.iservice.IGlobalCache;
import com.ivan.sunnyblog.web.dao.ArticleDaoExt;
import com.ivan.sunnyblog.web.dao.BaseDao;
import com.ivan.sunnyblog.web.dao.CategoryDaoExt;
import com.ivan.sunnyblog.web.services.iservice.IArticleService;
import com.ivan.sunnyblog.web.services.iservice.IBlogUserService;
import com.ivan.sunnyblog.web.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.impl.AvalonLogger;
import org.jooq.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ivan.sunnyblog.base.models.Tables.ARTICLE;
import static com.ivan.sunnyblog.web.contant.GlobalContant.ARTICLE_STATUS_PUBLISHED;
import static com.ivan.sunnyblog.web.contant.GlobalContant.ARTICLE_STATUS_SAVED;
import static org.jooq.impl.DSL.count;

import com.ivan.sunnyblog.base.models.tables.pojos.Article;

//import com.ivan.sunnyblog.base.models.tables.daos.ArticleDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: jinghaoliang
 * Date: 2021-09-25 3:25 p.m.
 **/

@Service
public class ArticleService  extends BaseService implements IArticleService {

    @Autowired
    private BaseDao dao;

    @Autowired
    private IBlogUserService blogUserService;

    @Autowired
    private ArticleDaoExt articleDao;
    @Autowired
    private CategoryDaoExt categoryDao;

    @Autowired
    private IGlobalCache redisCache;
    
    @Override
    public ResultInfo getArticleList(SearchVo searchVo) {

        Long userId = 100001l;

        int limit = searchVo.getLimit();
        int offset = (searchVo.getPage() - 1 ) * 10;


        List<Article> articles = dao.getDslContext().select().from(ARTICLE)
                .where(ARTICLE.USER_ID.eq(userId)).orderBy(ARTICLE.CREATE_DATE.desc())
                .offset(offset).limit(limit).fetchInto(Article.class);

        List<ArticleVo> articleVoList = copyList(articles);
        return ResultInfo.getSucResult(articleVoList);
    }


    @Override
    public ResultInfo getArticleDetail(Long id) {

        if(id == null){
            emptyPara("id");
        }

        Article article = articleDao.fetchOneByArticleId(id);
        if(article == null) {
            logger.error("could not find the article");
            return null;
        }

//        ArticleDetailVo articleDetailVo = new ArticleDetailVo();
//        BeanUtils.copyProperties(article, articleDetailVo);
//        articleDetailVo.setCreateDate(article.getCreateDate().toString());
//        articleDetailVo.setUpdateDate(article.getUpdateDate().toString());

        ArticleVo articleVo = new ArticleVo();
        articleVo = copy(article);
        articleVo.setContent(article.getContent());
//        articleVo.setCreateDate(article.get);


        String key = RedisKeyGenerator.generateBlogViewMapKey(id);
        if(redisCache.hgetAll(RedisKeyGenerator.CACHE_BLOG_VIEW_COUNT).containsKey(key)){
            redisCache.hIncr(RedisKeyGenerator.CACHE_BLOG_VIEW_COUNT, key, 1);
        }else{
            redisCache.hset(RedisKeyGenerator.CACHE_BLOG_VIEW_COUNT , key,1);
        }

        return ResultInfo.getSucResult(articleVo);

    }

    @Override
    public ResultInfo editArticle(ArticleDetailVo articleDetailVo) {

        if(StringUtils.isBlank(articleDetailVo.getTitle())){
            emptyPara("title");
        }

        if(StringUtils.isBlank(articleDetailVo.getDescription())){
            emptyPara("description");
        }

        if(StringUtils.isBlank(articleDetailVo.getContent())){
            emptyPara("content");
        }

        if(articleDetailVo.getCategoryId() == null) {
            emptyPara("categoryId");
        }


        dao.getDslContext().transaction((Configuration c) -> {
            if(articleDetailVo.getArticleId() != null){
                logger.info("updateing article");
                ArticleRecord updateRecord = dao.getDslContext().newRecord(ARTICLE);
                updateRecord.setArticleId(articleDetailVo.getArticleId());
                updateRecord.setDescription(articleDetailVo.getDescription());
                updateRecord.setContent(articleDetailVo.getContent());
                updateRecord.setTitle(articleDetailVo.getTitle());
                if(articleDetailVo.getFirstPicture() != null){
                    updateRecord.setFirstPicture(articleDetailVo.getFirstPicture());
                }

                updateRecord.setCategoryId(articleDetailVo.getCategoryId());

                if(articleDetailVo.getStatus() == ARTICLE_STATUS_PUBLISHED){
                    updateRecord.setStatus(ARTICLE_STATUS_PUBLISHED);
                }else{
                    updateRecord.setStatus(ARTICLE_STATUS_SAVED);
                }
                updateRecord.setUserId(100001l);
                updateRecord.setUpdateDate(new Date());

                updateRecord.update();
            }else{
                logger.info("createing new article");
                ArticleRecord articleRecord = dao.getDslContext().newRecord(ARTICLE);
                articleRecord.setDescription(articleDetailVo.getDescription());
                articleRecord.setContent(articleDetailVo.getContent());
                articleRecord.setTitle(articleDetailVo.getTitle());
                if(articleDetailVo.getFirstPicture() != null){
                    articleRecord.setFirstPicture(articleDetailVo.getFirstPicture());
                }

                articleRecord.setCategoryId(articleDetailVo.getCategoryId());

                if(articleDetailVo.getStatus() == ARTICLE_STATUS_PUBLISHED){
                    articleRecord.setStatus(ARTICLE_STATUS_PUBLISHED);
                }else{
                    articleRecord.setStatus(ARTICLE_STATUS_SAVED);
                }
                articleRecord.setUserId(100001l);



                articleRecord.insert();
            }
        });

//        if(articleDetailVo.getArticleId() != null){
//
//        }else{
//            ArticleRecord articleRecord = dao.getDslContext().newRecord(ARTICLE);
//            articleRecord.setDescription(articleDetailVo.getDescription());
//            articleRecord.setContent(articleDetailVo.getContent());
//            articleRecord.setTitle(articleDetailVo.getTitle());
//            if(articleDetailVo.getFirstPicture() != null){
//                articleRecord.setFirstPicture(articleDetailVo.getFirstPicture());
//            }
//
//            articleRecord.setCategoryId(articleDetailVo.getCategoryId());
//
//            if(articleDetailVo.getStatus() == ARTICLE_STATUS_PUBLISHED){
//                articleRecord.setStatus(ARTICLE_STATUS_PUBLISHED);
//            }else{
//                articleRecord.setStatus(ARTICLE_STATUS_SAVED);
//            }
//            articleRecord.setUserId(10001l);
//        }

        return ResultInfo.getSucResult(null);
    }


    @Override
    public ResultInfo getArticles(SearchVo searchVo) {

        System.out.println(searchVo);
        int limit = searchVo.getLimit();
        int offset = (searchVo.getPage() - 1 ) * limit;

        SelectConditionStep selectConditionStep = dao.getDslContext().select().from(ARTICLE)
                .where(ARTICLE.STATUS.eq(ARTICLE_STATUS_PUBLISHED));

        selectConditionStep = buildConditionStep(selectConditionStep, searchVo);

        List<Article> articles = selectConditionStep.orderBy(ARTICLE.CREATE_DATE.desc()).offset(offset)
                .limit(limit).fetchInto(Article.class);

        Long total = null;
        if(searchVo.getTotal() == null) {
            SelectConditionStep selectConditionStep2 = dao.getDslContext().select(count(ARTICLE.ARTICLE_ID)).from(ARTICLE)
                    .where(ARTICLE.STATUS.eq(ARTICLE_STATUS_PUBLISHED));

            selectConditionStep2 = buildConditionStep(selectConditionStep2, searchVo);

            total = (Long) selectConditionStep2.fetchOneInto(Long.class);
        }else{
            total = searchVo.getTotal();
        }

        List<ArticleVo> articleVoList = copyList(articles);

        ResultInfo resultInfo = ResultInfo.getResult();
        resultInfo.setData(articleVoList);
        resultInfo.setTotal(total);
        return resultInfo;
    }

    @Override
    public ResultInfo getHotArticles(SearchVo searchVo) {

        int limit = searchVo.getLimit();
        int offset = (searchVo.getPage() - 1 ) * limit;

        List<Article> articles = dao.getDslContext().select().from(ARTICLE)
                .where(ARTICLE.STATUS.eq(ARTICLE_STATUS_PUBLISHED)).orderBy(ARTICLE.VIEW_COUNT.desc())
                .offset(offset).limit(limit).fetchInto(Article.class);

        List<ArticleVo> articleVos = new ArrayList<>();
        for(Article article : articles){
            ArticleVo articleVo = new ArticleVo();
            articleVo.setArticleId(article.getArticleId());
            articleVo.setCreateDate(article.getCreateDate().toString());
            articleVo.setViewCount(article.getViewCount());
            articleVo.setTitle(article.getTitle());
            articleVos.add(articleVo);
        }

        return ResultInfo.getSucResult(articleVos);
    }

    public SelectConditionStep buildConditionStep(SelectConditionStep selectConditionStep, SearchVo searchVo){
        if(searchVo.getCategoryId() != null){
            selectConditionStep = selectConditionStep.and(ARTICLE.CATEGORY_ID.eq(searchVo.getCategoryId()));
        }


        return selectConditionStep;
    }

    public List<ArticleVo> copyList(List<Article> articleList){
        List<ArticleVo> articleVoList = new ArrayList<>();
        for(Article article : articleList){
            ArticleVo articleVo = copy(article);
            articleVoList.add(articleVo);
        }
        return articleVoList;
    }

    public ArticleVo copy(Article article){
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo, "content");
        articleVo.setCreateDate(article.getCreateDate().toString());
        articleVo.setUpdateDate(article.getUpdateDate().toString());
//        if(blogUserService.findUserById(article.getUserId()) )
        BlogUser blogUser = blogUserService.findUserById(article.getUserId());


        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUserId(blogUser.getUserId());
        userInfoVo.setUserName(blogUser.getUsername());
        userInfoVo.setNickName(blogUser.getNickname());
        userInfoVo.setAvatar(blogUser.getAvatar());
        articleVo.setUserInfo(userInfoVo);

        Category category = categoryDao.fetchOneByCategoryId(article.getCategoryId());
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setCategoryId(article.getCategoryId());
        categoryVo.setName(category.getName());

        articleVo.setCategory(categoryVo);
        return articleVo;
    }


    @Override
    public void updateViewCount(Long id, Integer count) {

        dao.getDslContext().transaction( configuration -> {
            Integer viewCount = dao.getDslContext().select(ARTICLE.VIEW_COUNT).from(ARTICLE)
                    .where(ARTICLE.ARTICLE_ID.eq(id)).fetchOneInto(Integer.class);
            ArticleRecord articleRecord = dao.getDslContext().newRecord(ARTICLE);
            articleRecord.setArticleId(id);
            articleRecord.setViewCount(count + viewCount);
            articleRecord.setUpdateDate(new Date());
//            logger.info("{}", articleRecord);
            articleRecord.update();
        });
    }
}
