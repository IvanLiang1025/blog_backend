package com.ivan.sunnyblog.web.vo;

/**
 * Author: jinghaoliang
 **/

public class CategoryVo {

    private Long categoryId;
    private String name;
    private Integer articleCount;

//    public CategoryVo(Long categoryId, String name) {
//        this.categoryId = categoryId;
//        this.name = name;
//    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    @Override
    public String toString() {
        return "CategoryVo{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", articleCount=" + articleCount +
                '}';
    }
}
