package com.ivan.sunnyblog.web.dao;

import com.ivan.sunnyblog.base.models.tables.daos.CommentDao;
import com.ivan.sunnyblog.web.vo.CommentListVo;
import com.ivan.sunnyblog.web.vo.SearchVo;
import org.jooq.Configuration;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.ivan.sunnyblog.base.models.Tables.COMMENT;
import static com.ivan.sunnyblog.web.contant.GlobalContant.COMMENT_ORIGINAL_PARENT_ID;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: jinghaoliang
 **/

@Component
public class CommentDaoExt extends CommentDao {

    @Autowired
    BaseDao dao;

    public CommentDaoExt() {
        super();
    }

    @Autowired
    public CommentDaoExt(Configuration configuration) {
        super(configuration);
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
                .and(COMMENT.PARENT_COMMENT_ID.eq(parentCommentId));

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

            List<CommentListVo> comments;
//            if(commentListVo.getParentCommentId() == COMMENT_ORIGINAL_PARENT_ID){
//                comments= getCommentListByArticleIdAndParentId(articleId, commentListVo.getCommentId(), searchVo);
//            }else{
//                comments= getCommentListByArticleIdAndParentId(articleId, commentListVo.getCommentId(), null);
//            }

            comments= getCommentListByArticleIdAndParentId(articleId, commentListVo.getCommentId(), null);
            commentListVo.setReplyComments(comments);
        }

        return commentListVos;

    }

    public Long getTotalOfOringalComments(Long articleId){
        Long total = dao.getDslContext().selectCount().from(COMMENT)
                .where(COMMENT.ARTICLE_ID.eq(articleId)).and(COMMENT.PARENT_COMMENT_ID.eq(COMMENT_ORIGINAL_PARENT_ID))
                .fetchOneInto(Long.class);
        return total;
    }


}
