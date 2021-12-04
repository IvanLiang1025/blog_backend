/*
 * This file is generated by jOOQ.
 */
package com.ivan.sunnyblog.base.models;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.blog_id_seq</code>
     */
    public static final Sequence<Long> BLOG_ID_SEQ = new SequenceImpl<Long>("blog_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.category_id_seq</code>
     */
    public static final Sequence<Long> CATEGORY_ID_SEQ = new SequenceImpl<Long>("category_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.comment_id_seq</code>
     */
    public static final Sequence<Long> COMMENT_ID_SEQ = new SequenceImpl<Long>("comment_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.user_id_seq</code>
     */
    public static final Sequence<Long> USER_ID_SEQ = new SequenceImpl<Long>("user_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));
}
