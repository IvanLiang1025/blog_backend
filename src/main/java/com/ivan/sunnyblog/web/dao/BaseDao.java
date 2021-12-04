package com.ivan.sunnyblog.web.dao;

import com.ivan.sunnyblog.base.utils.BaseLogger;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: jinghaoliang
 * Date: 2021-09-29 11:32 p.m.
 **/

@Component
public class BaseDao extends BaseLogger {

    @Autowired
    private DSLContext dslContext;


    public DSLContext getDslContext(){return this.dslContext;}


//    public DSLContext writeContect(){return this.dslContext;}

}
