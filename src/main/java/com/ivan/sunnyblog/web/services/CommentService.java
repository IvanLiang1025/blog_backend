package com.ivan.sunnyblog.web.services;

//import com.ivan.sunnyblog.base.models.tables.BlogUser;

import com.ivan.sunnyblog.base.models.tables.pojos.BlogUser;
import com.ivan.sunnyblog.base.models.tables.Comment;
import com.ivan.sunnyblog.base.models.tables.records.CommentRecord;

import static com.ivan.sunnyblog.base.models.Tables.COMMENT;
import static com.ivan.sunnyblog.web.constant.GlobalConstant.COMMENT_ORIGINAL_PARENT_ID;
import static com.ivan.sunnyblog.web.constant.GlobalConstant.COMMENT_STATUS_PUBLISED;

import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.dao.BaseDao;
import com.ivan.sunnyblog.web.dao.CommentDaoExt;
import com.ivan.sunnyblog.web.res.S3ResourceManager;
import com.ivan.sunnyblog.web.services.iservice.ICommentService;
import com.ivan.sunnyblog.web.vo.CommentListVo;
import com.ivan.sunnyblog.web.vo.CommentVo;
import com.ivan.sunnyblog.web.vo.SearchVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Author: jinghaoliang
 **/

@Service
public class CommentService extends BaseService implements ICommentService {


    @Autowired
    CommentDaoExt commentDao;

    @Autowired
    BaseDao dao;

    @Autowired
    S3ResourceManager s3ResourceManager;

    @Override
    public ResultInfo postComment(CommentVo commentVo) {

        if (StringUtils.isBlank(commentVo.getNickname())) {
            emptyPara("nickname");
        }
        if (StringUtils.isBlank(commentVo.getEmail())) emptyPara("email");
        if (StringUtils.isBlank(commentVo.getContent())) emptyPara("comment");

        commentVo.setAvatar(s3ResourceManager.getAvatarUrl());

        commentDao.addComment(commentVo);

        return ResultInfo.getSucResult(null);
    }



    @Override
    public ResultInfo postComment(CommentVo commentVo, BlogUser blogUser) {

        if (StringUtils.isBlank(commentVo.getContent())) emptyPara("comment");
        if(StringUtils.isBlank(commentVo.getEmail())){
            commentVo.setEmail(blogUser.getEmail());
        }

        if(StringUtils.isBlank(commentVo.getNickname())){
            commentVo.setNickname(blogUser.getNickname());
        }
        if(StringUtils.isBlank(commentVo.getAvatar())){
            commentVo.setAvatar(blogUser.getAvatar());
        }


        commentDao.addComment(commentVo);
        return ResultInfo.getSucResult(null);
    }



    @Override
    public ResultInfo getComments(Long articleId, SearchVo searchVo) {

        List<CommentListVo> comments = commentDao.getComments(articleId, COMMENT_ORIGINAL_PARENT_ID, searchVo);
        Long total;
        if (searchVo.getTotal() == null) {
            total = commentDao.getTotalOfOringalComments(articleId);
        } else {
            total = searchVo.getTotal();
        }

        ResultInfo resultInfo = ResultInfo.getResult();
        resultInfo.setData(comments);
        resultInfo.setTotal(total);
        return resultInfo;
    }


    @Override
    public ResultInfo getComments(SearchVo searchVo) {

//        List<CommentListVo> comments =  commentDao.getCommentList(searchVo);
        List<CommentListVo> comments = commentDao.getComments(searchVo);
        Long total;
        if (searchVo.getTotal() == null) {
            total = commentDao.getTotalComments();
        } else {
            total = searchVo.getTotal();
        }

        ResultInfo resultInfo = ResultInfo.getResult();
        resultInfo.setData(comments);
        resultInfo.setTotal(total);

        return resultInfo;
    }

    @Override
    public ResultInfo deleteComment(Long commentId) {
        if(commentId == null) {
            emptyPara("commentId");
        }

        commentDao.deleteComment(commentId);

        return ResultInfo.getSucResult(null);
    }
}
