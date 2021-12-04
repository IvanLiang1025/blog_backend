package com.ivan.sunnyblog.web.services.iservice;

import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.vo.CommentVo;
import com.ivan.sunnyblog.web.vo.SearchVo;

public interface ICommentService {

    ResultInfo postComment(CommentVo commentVo);

    /**
     * get comment list for public
     * @param articleId
     * @return
     */
    ResultInfo getComments(Long articleId, SearchVo searchVo);
}
