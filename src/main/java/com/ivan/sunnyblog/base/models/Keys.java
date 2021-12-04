/*
 * This file is generated by jOOQ.
 */
package com.ivan.sunnyblog.base.models;


import com.ivan.sunnyblog.base.models.tables.Article;
import com.ivan.sunnyblog.base.models.tables.BlogUser;
import com.ivan.sunnyblog.base.models.tables.Category;
import com.ivan.sunnyblog.base.models.tables.Comment;
import com.ivan.sunnyblog.base.models.tables.records.ArticleRecord;
import com.ivan.sunnyblog.base.models.tables.records.BlogUserRecord;
import com.ivan.sunnyblog.base.models.tables.records.CategoryRecord;
import com.ivan.sunnyblog.base.models.tables.records.CommentRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<ArticleRecord, Long> IDENTITY_ARTICLE = Identities0.IDENTITY_ARTICLE;
    public static final Identity<BlogUserRecord, Long> IDENTITY_BLOG_USER = Identities0.IDENTITY_BLOG_USER;
    public static final Identity<CategoryRecord, Long> IDENTITY_CATEGORY = Identities0.IDENTITY_CATEGORY;
    public static final Identity<CommentRecord, Long> IDENTITY_COMMENT = Identities0.IDENTITY_COMMENT;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ArticleRecord> ARTICLE_PKEY = UniqueKeys0.ARTICLE_PKEY;
    public static final UniqueKey<BlogUserRecord> BLOG_USER_PKEY = UniqueKeys0.BLOG_USER_PKEY;
    public static final UniqueKey<CategoryRecord> CATEGORY_PKEY = UniqueKeys0.CATEGORY_PKEY;
    public static final UniqueKey<CommentRecord> COMMENT_PKEY = UniqueKeys0.COMMENT_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<ArticleRecord, Long> IDENTITY_ARTICLE = Internal.createIdentity(Article.ARTICLE, Article.ARTICLE.ARTICLE_ID);
        public static Identity<BlogUserRecord, Long> IDENTITY_BLOG_USER = Internal.createIdentity(BlogUser.BLOG_USER, BlogUser.BLOG_USER.USER_ID);
        public static Identity<CategoryRecord, Long> IDENTITY_CATEGORY = Internal.createIdentity(Category.CATEGORY, Category.CATEGORY.CATEGORY_ID);
        public static Identity<CommentRecord, Long> IDENTITY_COMMENT = Internal.createIdentity(Comment.COMMENT, Comment.COMMENT.COMMENT_ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<ArticleRecord> ARTICLE_PKEY = Internal.createUniqueKey(Article.ARTICLE, "article_pkey", Article.ARTICLE.ARTICLE_ID);
        public static final UniqueKey<BlogUserRecord> BLOG_USER_PKEY = Internal.createUniqueKey(BlogUser.BLOG_USER, "blog_user_pkey", BlogUser.BLOG_USER.USER_ID);
        public static final UniqueKey<CategoryRecord> CATEGORY_PKEY = Internal.createUniqueKey(Category.CATEGORY, "category_pkey", Category.CATEGORY.CATEGORY_ID);
        public static final UniqueKey<CommentRecord> COMMENT_PKEY = Internal.createUniqueKey(Comment.COMMENT, "comment_pkey", Comment.COMMENT.COMMENT_ID);
    }
}
