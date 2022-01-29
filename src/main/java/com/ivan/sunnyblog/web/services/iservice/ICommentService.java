package com.ivan.sunnyblog.web.services.iservice;

import com.ivan.sunnyblog.base.models.tables.pojos.BlogUser;
import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.vo.CommentVo;
import com.ivan.sunnyblog.web.vo.SearchVo;

public interface ICommentService {

    /**
     * visitor post comment
     * @param commentVo
     * @return
     */
    ResultInfo postComment(CommentVo commentVo);


    /**
     * admins post comment
     * @param commentVo
     * @param blogUser
     * @return
     */
    ResultInfo postComment(CommentVo commentVo, BlogUser blogUser);

    /**
     * get comment list of the specific article
     * @param articleId
     * @return
     */
    ResultInfo getComments(Long articleId, SearchVo searchVo);

    /**
     * get all comments
     * @param searchVo
     * @return
     */
    ResultInfo getComments(SearchVo searchVo);

    /**
     * delete comment with the commentId and its child comment
     * @param commentId
     * @return
     */
    ResultInfo deleteComment(Long commentId);

}
