package com.ivan.sunnyblog.web.dao;

import com.ivan.sunnyblog.base.models.tables.daos.ArticleDao;
import com.ivan.sunnyblog.base.models.tables.daos.CategoryDao;
import org.jooq.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: jinghaoliang
 **/
@Component
public class CategoryDaoExt extends CategoryDao {

    public CategoryDaoExt() {
        super();
    }

    @Autowired
    public CategoryDaoExt(Configuration configuration) {
        super(configuration);
    }
}

//
//@Component
//public class CategoryDaoExt extends ArticleDao {
//
//    public ArticleDaoExt() {
//        super();
//    }
//
//    @Autowired
//    public ArticleDaoExt(Configuration configuration) {
//        super(configuration);
//    }
//
//
//}
