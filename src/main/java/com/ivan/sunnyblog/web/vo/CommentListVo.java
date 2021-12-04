package com.ivan.sunnyblog.web.vo;

import java.util.Date;
import java.util.List;

/**
 * Author: jinghaoliang
 **/

public class CommentListVo extends CommentVo{

    private Long commentId;
    private List<CommentListVo> replyComments;
    private Date createdate;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public List<CommentListVo> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<CommentListVo> replyComments) {
        this.replyComments = replyComments;
    }

    public Date getCreateDate() {
        return createdate;
    }

    public void setCreateDate(Date createdate) {
        this.createdate = createdate;
    }

    @Override
    public String toString() {
        return "CommentListVo{" +
                "commentId=" + commentId +

                "createdate=" + createdate +
                ", replyComments=" + replyComments +
                ", CommentVo=" + super.toString() +
                '}';
    }
}
