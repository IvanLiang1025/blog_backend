package com.ivan.sunnyblog.web.dao;

import com.ivan.sunnyblog.base.models.tables.daos.ArticleDao;
import org.jooq.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: jinghaoliang
 * Date: 2021-10-04 4:07 p.m.
 **/

@Component
public class ArticleDaoExt extends ArticleDao {

    public ArticleDaoExt() {
        super();
    }

    @Autowired
    public ArticleDaoExt(Configuration configuration) {
        super(configuration);
    }


}
