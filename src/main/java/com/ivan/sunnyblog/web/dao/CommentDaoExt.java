package com.ivan.sunnyblog.web.dao;

import com.ivan.sunnyblog.base.models.tables.BlogUser;
import com.ivan.sunnyblog.base.models.tables.daos.CommentDao;
import com.ivan.sunnyblog.base.models.tables.records.CommentRecord;
import com.ivan.sunnyblog.web.res.S3ResourceManager;
import com.ivan.sunnyblog.web.vo.CommentListVo;
import com.ivan.sunnyblog.web.vo.CommentVo;
import com.ivan.sunnyblog.web.vo.SearchVo;
import org.apache.commons.lang3.StringUtils;
import org.jooq.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.ivan.sunnyblog.base.models.Tables.COMMENT;
import static com.ivan.sunnyblog.base.models.tables.Article.ARTICLE;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.ivan.sunnyblog.web.constant.GlobalConstant.*;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

/**
 * Author: jinghaoliang
 **/

@Component
public class CommentDaoExt extends CommentDao {


    static Logger logger = LoggerFactory.getLogger(CommentDaoExt.class);

    @Autowired
    BaseDao dao;

    @Autowired
    S3ResourceManager s3ResourceManager;

    public CommentDaoExt() {
        super();
    }

    @Autowired
    public CommentDaoExt(Configuration configuration) {
        super(configuration);
    }


    public void addComment(CommentVo commentVo){

        dao.getDslContext().transaction(Configuration -> {
            CommentRecord commentRecord = dao.getDslContext().newRecord(COMMENT);
            commentRecord.setArticleId(commentVo.getArticleId());
            commentRecord.setContent(commentVo.getContent());
            commentRecord.setEmail(commentVo.getEmail());
            commentRecord.setNickname(commentVo.getNickname());
            commentRecord.setCreatedate(new Date());

            commentRecord.setAvatar(commentVo.getAvatar());

            if (commentVo.getStatus() != null) {
                commentRecord.setStatus(commentVo.getStatus());
            } else {
                commentRecord.setStatus(COMMENT_STATUS_PUBLISED);
            }

            if (commentVo.getParentCommentId() != null) {
                commentRecord.setParentCommentId(commentVo.getParentCommentId());
            } else {
                commentRecord.setParentCommentId(COMMENT_ORIGINAL_PARENT_ID);
            }

            if (StringUtils.isNotBlank(commentVo.getParentCommentNickname())) {
                commentRecord.setParentCommentNickname(commentVo.getParentCommentNickname());
            }

            commentRecord.insert();
        });


    }

    public List<CommentListVo> getComments(Long articleId, Long parentCommentId, SearchVo searchVo){
        List<CommentListVo> comments = getCommentListByArticleIdAndParentId(articleId, parentCommentId, searchVo);
//        for(CommentListVo comment: comments){
//            List<CommentListVo> temp = new ArrayList<>();
//            getReplyComments(temp, comment.getReplyComments());
//            comment.setReplyComments(temp);
//        }
        return comments;
    }

    public List<CommentListVo> getComments(SearchVo searchVo){
        int offset = (searchVo.getPage() - 1) * searchVo.getLimit();
        int limit = searchVo.getLimit();

        List<CommentListVo> commentListVos = dao.getDslContext().select(COMMENT.asterisk(), ARTICLE.TITLE).from(COMMENT.join(ARTICLE).on(COMMENT.ARTICLE_ID.eq(ARTICLE.ARTICLE_ID)))
                .where(COMMENT.STATUS.ge(COMMENT_STATUS_PUBLISED)).orderBy(COMMENT.CREATEDATE.desc())
                .offset(offset).limit(limit).fetchInto(CommentListVo.class);

        logger.info("comments: {}", commentListVos);

        return commentListVos;
    }




    /**
     *  get comment with the id and its child comment
     * @param commentId
     * @return
     */
    public List<CommentListVo> getChildComments(Long commentId){

        CommonTableExpression<?> cte = name("t").as(
                select(COMMENT.asterisk())
                        .from(COMMENT)
                        .where(COMMENT.COMMENT_ID.eq(commentId))
                        .union(
                                select(COMMENT.asterisk())
                                        .from(COMMENT
                                                .join(table(name("t")))
                                                .on(COMMENT.PARENT_COMMENT_ID.eq(field(name("t", "comment_id"), BIGINT))
                                        ))
                        )
//                                        .on(field(name("t", "parent_comment_id"), BIGINT)
//                                                .eq(COMMENT.COMMENT_ID)))
        );

        List<CommentListVo> list = dao.getDslContext().withRecursive(cte)
                .selectFrom(cte)
                .orderBy(cte.field("comment_id"))
                .fetchInto(CommentListVo.class);

        return list;
    }


    /**
     * @param commentId - the comment with the id and its child comments are going to be deleted
     */
    public void deleteComment(Long commentId){
        List<CommentListVo> childComments = getChildComments(commentId);

        if(!childComments.isEmpty() && childComments!=null){
            dao.getDslContext().transaction(Configuration -> {
                Iterator<CommentListVo> iterator = childComments.iterator();
                while(iterator.hasNext()){
                    CommentListVo comment = iterator.next();
                    logger.info("delete comment(change status), commentId={}", comment.getCommentId());
                    dao.getDslContext().update(COMMENT).set(COMMENT.STATUS, COMMENT_STATUS_DELETED)
                            .where(COMMENT.COMMENT_ID.eq(comment.getCommentId())).execute();
                }
            });
        }

    }

    public Long getTotalComments(){
        Long total = dao.getDslContext().selectCount().from(COMMENT)
                .where(COMMENT.STATUS.ge(COMMENT_STATUS_PUBLISED)).fetchOneInto(Long.class);

        return total;
    }



    /**
     * add all replies to a list recursively
     * @param temp
     * @param replyComments
     */
    private void getReplyComments(List<CommentListVo> temp, List<CommentListVo> replyComments){
        for(CommentListVo c : replyComments){
//            temp.add(c);
            getReplyComments(temp, c.getReplyComments());
            temp.add(c);
        }
    }


    public List<CommentListVo> getCommentListByArticleIdAndParentId(Long articleId, Long parentCommentId, SearchVo searchVo){

//        List<CommentListVo> commentListVos = dao.getDslContext().select().from(COMMENT).where(COMMENT.ARTICLE_ID.eq(articleId))
//                .and(COMMENT.PARENT_COMMENT_ID.eq(parentCommentId)).orderBy(COMMENT.CREATEDATE.desc()).fetchInto(CommentListVo.class);
        SelectConditionStep<Record> selectConditionStep = dao.getDslContext().select().from(COMMENT).where(COMMENT.ARTICLE_ID.eq(articleId))
                .and(COMMENT.PARENT_COMMENT_ID.eq(parentCommentId)).and(COMMENT.STATUS.eq(COMMENT_STATUS_PUBLISED));

        List<CommentListVo> commentListVos;
        // PAGINATION BASED ON ORIGINAL COMMENT (parentCommentId = 0)
        if(searchVo != null){
            int offset = (searchVo.getPage() - 1) * searchVo.getLimit();
            int limit = searchVo.getLimit();
            commentListVos = selectConditionStep.orderBy(COMMENT.CREATEDATE.desc()).offset(offset).limit(limit).fetchInto(CommentListVo.class);
        }else{
            commentListVos = selectConditionStep.orderBy(COMMENT.CREATEDATE.desc()).fetchInto(CommentListVo.class);
        }

        for(CommentListVo commentListVo : commentListVos){

            List<CommentListVo> comments = new ArrayList<>();

            getChildren(commentListVo, comments);
            commentListVo.setReplyComments(comments);
        }

        return commentListVos;

    }


    public void getChildren(CommentListVo root, List<CommentListVo>  children){
        List<CommentListVo> commentListVos = dao.getDslContext().select().from(COMMENT)
                .where(COMMENT.PARENT_COMMENT_ID.eq(root.getCommentId())).and(COMMENT.STATUS.eq(COMMENT_STATUS_PUBLISED))
                .orderBy(COMMENT.CREATEDATE.asc()).fetchInto(CommentListVo.class);

        if(commentListVos == null || commentListVos.size() == 0) return;

        children.addAll(commentListVos);
        for(CommentListVo commentListVo : commentListVos){
            getChildren(commentListVo, children);
        }

    }

    public Long getTotalOfOringalComments(Long articleId){
        Long total = dao.getDslContext().selectCount().from(COMMENT)
                .where(COMMENT.ARTICLE_ID.eq(articleId)).and(COMMENT.PARENT_COMMENT_ID.eq(COMMENT_ORIGINAL_PARENT_ID))
                .fetchOneInto(Long.class);
        return total;
    }



}
