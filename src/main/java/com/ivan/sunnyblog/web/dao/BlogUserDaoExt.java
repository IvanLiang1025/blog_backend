package com.ivan.sunnyblog.web.dao;

import com.ivan.sunnyblog.base.dao.JooqDao;
import com.ivan.sunnyblog.base.models.tables.daos.BlogUserDao;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Author: jinghaoliang
 * Date: 2021-09-29 4:09 p.m.
 **/

@Repository
public class BlogUserDaoExt extends BlogUserDao {
    public BlogUserDaoExt() {
        super();
    }

    @Autowired
    public BlogUserDaoExt(Configuration configuration) {
        super(configuration);
    }


}
