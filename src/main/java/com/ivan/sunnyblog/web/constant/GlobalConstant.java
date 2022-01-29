package com.ivan.sunnyblog.web.constant;

/**
 * Author: jinghaoliang
 **/

public class GlobalConstant {

    public final static String ACCESS_TOKEN = "x_access_token";

    public static final Short ARTICLE_STATUS_SAVED = 0;
    public static final Short ARTICLE_STATUS_PUBLISHED = 1;
    public static final Short ARTICLE_STATUS_DELETED  = -1;

    public static final Short ARTICLE_TYPE_ORIGINAL = 1;
    public static final Short ARTICLE_TYPE_REPRODUCE = 2;


    // status of comment
    public static final Short COMMENT_STATUS_PUBLISED = 0;
    public static final Short COMMENT_STATUS_HIDDEN = 1;
    public static final Short COMMENT_STATUS_DELETED = -1;

    public static final Long COMMENT_ORIGINAL_PARENT_ID = 0l;

}
