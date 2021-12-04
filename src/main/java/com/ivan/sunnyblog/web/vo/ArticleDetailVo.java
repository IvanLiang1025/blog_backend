package com.ivan.sunnyblog.web.vo;

/**
 * Author: jinghaoliang
 **/

public class ArticleDetailVo extends BaseArticleVo{

    private Long categoryId;


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }


    @Override
    public String toString() {
        return "ArticleDetailVo{" +
                "categoryId=" + categoryId +
                ", " + super.toString() + '}';
    }
}
