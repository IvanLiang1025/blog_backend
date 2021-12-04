package com.ivan.sunnyblog.web.services.iservice;

import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.vo.ArticleDetailVo;
import com.ivan.sunnyblog.web.vo.ArticleVo;
import com.ivan.sunnyblog.web.vo.SearchVo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

public interface IArticleService {

    /**
    list of all articles
     */
    ResultInfo getArticleList(SearchVo searchVo);

    /**
     * get article detail
     */
    ResultInfo getArticleDetail(Long id);

    ResultInfo editArticle(ArticleDetailVo ArticleDetailVo);

    /**
     * get list of articles that will be shown to guest
     * @param searchVo
     * @return
     */
    ResultInfo getArticles(SearchVo searchVo);

    ResultInfo getHotArticles(SearchVo searchVo);

    void updateViewCount(Long id, Integer count);




}
