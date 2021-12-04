package com.ivan.sunnyblog.base.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;

/**
 * Author: jinghaoliang
 * Date: 2021-09-29 3:57 p.m.
 **/

public class JooqDao {

    private final DSLContext create;

    @Autowired
    public JooqDao(DSLContext dslContext) { this.create = dslContext;}

//    @PreDestroy
//    public void onDestroy(){
//




}
