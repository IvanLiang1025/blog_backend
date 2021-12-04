package com.ivan.sunnyblog.web.services;

import com.ivan.sunnyblog.base.models.tables.records.CommentRecord;

import static com.ivan.sunnyblog.base.models.Tables.COMMENT;
import static com.ivan.sunnyblog.web.contant.GlobalContant.COMMENT_ORIGINAL_PARENT_ID;
import static com.ivan.sunnyblog.web.contant.GlobalContant.COMMENT_STATUS_PUBLISED;

import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.dao.BaseDao;
import com.ivan.sunnyblog.web.dao.CommentDaoExt;
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

    @Override
    public ResultInfo postComment(CommentVo commentVo) {

        if(StringUtils.isBlank(commentVo.getNickname())) {
            emptyPara("nickname");
        }
        if(StringUtils.isBlank(commentVo.getEmail())) emptyPara("email");
        if(StringUtils.isBlank(commentVo.getContent())) emptyPara("comment");

        CommentRecord commentRecord = dao.getDslContext().newRecord(COMMENT);
        commentRecord.setArticleId(commentVo.getArticleId());
        commentRecord.setContent(commentVo.getContent());
        commentRecord.setEmail(commentVo.getEmail());
        commentRecord.setNickname(commentVo.getNickname());
        commentRecord.setCreatedate(new Date());

        if(commentVo.getStatus() != null) {
            commentRecord.setStatus(commentVo.getStatus());
        }else{
            commentRecord.setStatus(COMMENT_STATUS_PUBLISED);
        }

        if(commentVo.getParentCommentId() != null){
            commentRecord.setParentCommentId(commentVo.getParentCommentId());
        }else{
            commentRecord.setParentCommentId(COMMENT_ORIGINAL_PARENT_ID);
        }

        if(StringUtils.isNotBlank(commentVo.getParentCommentNickname())){
            commentRecord.setParentCommentNickname(commentVo.getParentCommentNickname());
        }

        commentRecord.insert();

        return ResultInfo.getSucResult(null);
    }


    @Override
    public ResultInfo getComments(Long articleId, SearchVo searchVo) {

        List<CommentListVo> comments = commentDao.getComments(articleId, COMMENT_ORIGINAL_PARENT_ID, searchVo);
        Long total;
        if(searchVo.getTotal() == null){
            total = commentDao.getTotalOfOringalComments(articleId);
        }else {
            total = searchVo.getTotal();
        }

        ResultInfo resultInfo = ResultInfo.getResult();
        resultInfo.setData(comments);
        resultInfo.setTotal(total);
//        System.out.println();

        return resultInfo;
    }
}
