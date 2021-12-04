package com.ivan.sunnyblog.web.services;

import com.ivan.sunnyblog.base.models.tables.Article;
import com.ivan.sunnyblog.base.models.tables.pojos.Category;
import com.ivan.sunnyblog.base.models.tables.records.CategoryRecord;
import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.dao.BaseDao;
import com.ivan.sunnyblog.web.services.iservice.ICategoryService;
import com.ivan.sunnyblog.web.vo.CategoryVo;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Configuration;
import org.jooq.Field;
import org.jooq.TransactionalRunnable;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ivan.sunnyblog.base.models.Tables.*;

import java.util.List;

import static com.ivan.sunnyblog.base.models.Tables.ARTICLE;
import static com.ivan.sunnyblog.base.models.Tables.CATEGORY;
import static com.ivan.sunnyblog.web.contant.GlobalContant.ARTICLE_STATUS_PUBLISHED;

/**
 * Author: jinghaoliang
 * Date: 2021-10-02 10:55 p.m.
 **/

@Service
public class CategoryService extends BaseService implements ICategoryService {

    @Autowired
    private BaseDao dao;

    @Override
    public ResultInfo getCategoryList() {

        List<Category> categories = dao.getDslContext().select().from(CATEGORY).fetchInto(Category.class);

        return ResultInfo.getSucResult(categories);

    }

    @Override
    public ResultInfo editCategory(CategoryVo categoryVo) {
        System.out.println(categoryVo);

        if(StringUtils.isBlank(categoryVo.getName())){
            emptyPara("name");
        }
        dao.getDslContext().transaction((Configuration c) -> {
            CategoryRecord categoryRecord = dao.getDslContext().newRecord(CATEGORY);
            if(categoryVo.getCategoryId() == null) {
                categoryRecord.setName(categoryVo.getName());
                categoryRecord.insert();
            }else{
                categoryRecord.setCategoryId(categoryVo.getCategoryId());
                categoryRecord.setName(categoryVo.getName());
                categoryRecord.update();
            }
        });
        return ResultInfo.getSucResult(null);
    }


    @Override
    public ResultInfo deleteCategory(Long id) {

        if(id == null) {
            emptyPara("categoryId");
        }
//        int deletedRecord;
        dao.getDslContext().transaction((Configuration c )-> {
           int deletedRecord = dao.getDslContext().delete(CATEGORY).where(CATEGORY.CATEGORY_ID.eq(id)).execute();
        });
//        System.out.println(execute);
        return ResultInfo.getSucResult(null);
    }

    @Override
    public ResultInfo getCategories() {

//        Field<Integer> arti

        List<CategoryVo> categoryVoList = dao.getDslContext().select(CATEGORY.CATEGORY_ID, CATEGORY.NAME, DSL.count(ARTICLE.ARTICLE_ID).as("articleCount"))
                .from(CATEGORY.join(ARTICLE).on(CATEGORY.CATEGORY_ID.eq(ARTICLE.CATEGORY_ID)))
                .where(ARTICLE.STATUS.eq(ARTICLE_STATUS_PUBLISHED))
                .groupBy(CATEGORY.CATEGORY_ID).orderBy(CATEGORY.CATEGORY_ID.desc()).fetchInto(CategoryVo.class);

//        System.out.println(categoryVoList);

        ResultInfo resultInfo = ResultInfo.getSucResult(categoryVoList);

        return resultInfo;
    }
}
