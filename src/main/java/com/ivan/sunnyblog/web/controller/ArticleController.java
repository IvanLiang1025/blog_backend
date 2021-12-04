package com.ivan.sunnyblog.web.controller;

import com.ivan.sunnyblog.base.models.tables.pojos.Article;
import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.services.ArticleService;
import com.ivan.sunnyblog.web.services.iservice.IArticleService;
import com.ivan.sunnyblog.web.vo.ArticleDetailVo;
import com.ivan.sunnyblog.web.vo.ArticleVo;
import com.ivan.sunnyblog.web.vo.SearchVo;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * Author: jinghaoliang
 **/

@RestController
@RequestMapping("api")
public class ArticleController {
    static Logger logger = LoggerFactory.getLogger(ArticleController.class);

//    @Autowired
//    private MessageSource msgSource;
    @Autowired
    private IArticleService articleService;


    @RequestMapping(value = "/admin/article", method = RequestMethod.GET)
    public ResultInfo getArticleList(SearchVo searchVo){
        return articleService.getArticleList(searchVo);
    }

    @RequestMapping(value = "/admin/article", method = RequestMethod.POST)
    public ResultInfo editArticle(@RequestBody ArticleDetailVo articleDetailVo){
//        logger.info("{}", articleDetailVo);

        return articleService.editArticle(articleDetailVo);
    }

    @RequestMapping(value = "/admin/article/{id}", method = RequestMethod.GET)
    public ResultInfo getArticleDetail(@PathVariable(name = "id") Long id){
        ResultInfo resultInfo = articleService.getArticleDetail(id);
        return resultInfo;
    }


    // articles for guest
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public ResultInfo getPublicArticles(SearchVo searchVo){
        System.out.println("get articles for guests");
//        s3Manager = new S3Manager()


        return articleService.getArticles(searchVo);
    }


    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public ResultInfo getPublicArticleDetail(@PathVariable(name = "id") Long id){
        ResultInfo resultInfo = articleService.getArticleDetail(id);
        return resultInfo;
    }

    @RequestMapping(value = "/articles/hot", method = RequestMethod.GET)
    public ResultInfo getHotArticles(){
        SearchVo searchVo = new SearchVo();
        searchVo.setPage(1);
        searchVo.setLimit(5);
        ResultInfo resultInfo = articleService.getHotArticles(searchVo);
        return resultInfo;
    }



}
