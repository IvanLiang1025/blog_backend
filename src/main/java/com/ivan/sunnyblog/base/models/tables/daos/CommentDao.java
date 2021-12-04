/*
 * This file is generated by jOOQ.
 */
package com.ivan.sunnyblog.base.models.tables.daos;


import com.ivan.sunnyblog.base.models.tables.Comment;
import com.ivan.sunnyblog.base.models.tables.records.CommentRecord;

import java.util.Date;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CommentDao extends DAOImpl<CommentRecord, com.ivan.sunnyblog.base.models.tables.pojos.Comment, Long> {

    /**
     * Create a new CommentDao without any configuration
     */
    public CommentDao() {
        super(Comment.COMMENT, com.ivan.sunnyblog.base.models.tables.pojos.Comment.class);
    }

    /**
     * Create a new CommentDao with an attached configuration
     */
    public CommentDao(Configuration configuration) {
        super(Comment.COMMENT, com.ivan.sunnyblog.base.models.tables.pojos.Comment.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(com.ivan.sunnyblog.base.models.tables.pojos.Comment object) {
        return object.getCommentId();
    }

    /**
     * Fetch records that have <code>comment_id IN (values)</code>
     */
    public List<com.ivan.sunnyblog.base.models.tables.pojos.Comment> fetchByCommentId(Long... values) {
        return fetch(Comment.COMMENT.COMMENT_ID, values);
    }

    /**
     * Fetch a unique record that has <code>comment_id = value</code>
     */
    public com.ivan.sunnyblog.base.models.tables.pojos.Comment fetchOneByCommentId(Long value) {
        return fetchOne(Comment.COMMENT.COMMENT_ID, value);
    }

    /**
     * Fetch records that have <code>article_id IN (values)</code>
     */
    public List<com.ivan.sunnyblog.base.models.tables.pojos.Comment> fetchByArticleId(Long... values) {
        return fetch(Comment.COMMENT.ARTICLE_ID, values);
    }

    /**
     * Fetch records that have <code>nickname IN (values)</code>
     */
    public List<com.ivan.sunnyblog.base.models.tables.pojos.Comment> fetchByNickname(String... values) {
        return fetch(Comment.COMMENT.NICKNAME, values);
    }

    /**
     * Fetch records that have <code>email IN (values)</code>
     */
    public List<com.ivan.sunnyblog.base.models.tables.pojos.Comment> fetchByEmail(String... values) {
        return fetch(Comment.COMMENT.EMAIL, values);
    }

    /**
     * Fetch records that have <code>content IN (values)</code>
     */
    public List<com.ivan.sunnyblog.base.models.tables.pojos.Comment> fetchByContent(String... values) {
        return fetch(Comment.COMMENT.CONTENT, values);
    }

    /**
     * Fetch records that have <code>avatar IN (values)</code>
     */
    public List<com.ivan.sunnyblog.base.models.tables.pojos.Comment> fetchByAvatar(String... values) {
        return fetch(Comment.COMMENT.AVATAR, values);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<com.ivan.sunnyblog.base.models.tables.pojos.Comment> fetchByStatus(Short... values) {
        return fetch(Comment.COMMENT.STATUS, values);
    }

    /**
     * Fetch records that have <code>parent_comment_id IN (values)</code>
     */
    public List<com.ivan.sunnyblog.base.models.tables.pojos.Comment> fetchByParentCommentId(Long... values) {
        return fetch(Comment.COMMENT.PARENT_COMMENT_ID, values);
    }

    /**
     * Fetch records that have <code>parent_comment_nickname IN (values)</code>
     */
    public List<com.ivan.sunnyblog.base.models.tables.pojos.Comment> fetchByParentCommentNickname(String... values) {
        return fetch(Comment.COMMENT.PARENT_COMMENT_NICKNAME, values);
    }

    /**
     * Fetch records that have <code>createdate IN (values)</code>
     */
    public List<com.ivan.sunnyblog.base.models.tables.pojos.Comment> fetchByCreatedate(Date... values) {
        return fetch(Comment.COMMENT.CREATEDATE, values);
    }
}
