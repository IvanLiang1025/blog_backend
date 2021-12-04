package com.ivan.sunnyblog.web.vo;

/**
 * Author: jinghaoliang
 **/

public class CommentVo {

    private Long parentCommentId;
    private Long articleId;
    private String email;
    private String nickname;
    private String parentCommentNickname;
    private String content;
    private Short status;

//    private List<Co>

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getParentCommentNickname() {
        return parentCommentNickname;
    }

    public void setParentCommentNickname(String parentCommentNickname) {
        this.parentCommentNickname = parentCommentNickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommentVo{" +
                "parentCommentId=" + parentCommentId +
                ", articleId=" + articleId +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", parentCommentNickname='" + parentCommentNickname + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
