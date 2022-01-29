package com.ivan.sunnyblog.web.controller;

import com.ivan.sunnyblog.base.models.tables.pojos.BlogUser;
import com.ivan.sunnyblog.base.models.tables.pojos.Comment;
import com.ivan.sunnyblog.base.utils.JwtUtil;
import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.services.iservice.IBlogUserService;
import com.ivan.sunnyblog.web.services.iservice.ICommentService;
import com.ivan.sunnyblog.web.vo.CommentVo;
import com.ivan.sunnyblog.web.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import java.util.Map;

import static com.ivan.sunnyblog.web.constant.GlobalConstant.ACCESS_TOKEN;

/**
 * Author: jinghaoliang
 **/

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    ICommentService commentService;

    @Autowired
    IBlogUserService blogUserService;
    
    JwtUtil jwtUtil;


    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResultInfo postComment(@RequestBody CommentVo commentVo){

        return commentService.postComment(commentVo);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultInfo getComments(@PathVariable(name = "id") Long articleId, SearchVo searchVo){
//        System.out.println(searchVo);
        return commentService.getComments(articleId, searchVo);
    }

    @RequestMapping(value = "/admin/comments", method = RequestMethod.GET)
    public ResultInfo getComments(SearchVo searchVo){
//        System.out.println(searchVo);
        return commentService.getComments(searchVo);
    }

    /**
     * used for admins to reply comment
     * @param commentVo
     * @return
     */
    @RequestMapping(value = "/admin/comment", method = RequestMethod.POST)
    public ResultInfo postAdminComment(@RequestBody CommentVo commentVo, HttpServletRequest request){
//        System.out.println(request);
        String token = request.getHeader(ACCESS_TOKEN);
//        System.out.println(authorization);
        Map<String, Object> stringObjectMap = JwtUtil.verifyToken(token);

        Long userId =null;
        if(stringObjectMap.get("userId") != null){
            userId = Long.parseLong(stringObjectMap.get("userId").toString());
        }

        BlogUser blogUser = blogUserService.findUserById(userId);

        return commentService.postComment(commentVo, blogUser);

    }

    @RequestMapping(value = "/admin/comment/delete", method = RequestMethod.POST)
    public ResultInfo deleteComment(@RequestBody Comment comment){

//        System.out.println(comment);
        return commentService.deleteComment(comment.getCommentId());
//        return null;
    }
}
