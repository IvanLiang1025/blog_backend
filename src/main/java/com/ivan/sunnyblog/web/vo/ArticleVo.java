package com.ivan.sunnyblog.web.vo;

import java.util.Date;

/**
 * Author: jinghaoliang
 * Date: 2021-10-01 10:54 p.m.
 **/

public class ArticleVo extends BaseArticleVo {
//    private Long    articleId;
//    private Short   status;
//    private String  title;
//    private String  content;
//    private String  description;
//    private String  firstPicture;
//    private Integer viewCount;
//    private String  Author;
//    private Long    userId;
    private UserInfoVo userInfo;
    private CategoryVo category;
//    private Long    categoryId;
//    private String createDate;
//    private String updateDate;


//    public Long getArticleId() {
//        return articleId;
//    }
//
//    public void setArticleId(Long articleId) {
//        this.articleId = articleId;
//    }
//
//    public Short getStatus() {
//        return status;
//    }
//
//    public void setStatus(Short status) {
//        this.status = status;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getFirstPicture() {
//        return firstPicture;
//    }
//
//    public void setFirstPicture(String firstPicture) {
//        this.firstPicture = firstPicture;
//    }
//
//    public Integer getViewCount() {
//        return viewCount;
//    }
//
//    public void setViewCount(Integer viewCount) {
//        this.viewCount = viewCount;
//    }
//
//    public String getAuthor() {
//        return Author;
//    }
//
//    public void setAuthor(String author) {
//        Author = author;
//    }
//
//
//    public String getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(String createDate) {
//        this.createDate = createDate;
//    }
//
//    public String getUpdateDate() {
//        return updateDate;
//    }
//
//    public void setUpdateDate(String updateDate) {
//        this.updateDate = updateDate;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public UserInfoVo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoVo userInfoVo) {
        this.userInfo = userInfoVo;
    }

    public CategoryVo getCategory() {
        return category;
    }

    public void setCategory(CategoryVo categoryVo) {
        this.category = categoryVo;
    }

    @Override
    public String toString() {
        return "ArticleVo{" +
//                "articleId=" + articleId +
//                ", status=" + status +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                ", description='" + description + '\'' +
//                ", firstPicture='" + firstPicture + '\'' +
//                ", viewCount=" + viewCount +
//                ", Author='" + Author + '\'' +
                "userInfo=" + userInfo +
                ", category=" + category +
                ", "+ super.toString() +
//                ", createDate='" + createDate + '\'' +
//                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
