package com.ivan.sunnyblog.web.controller;

import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.services.iservice.ICommentService;
import com.ivan.sunnyblog.web.vo.CommentVo;
import com.ivan.sunnyblog.web.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Author: jinghaoliang
 **/

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    ICommentService commentService;


    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResultInfo postComment(@RequestBody CommentVo commentVo){

        return commentService.postComment(commentVo);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultInfo getComments(@PathVariable(name = "id") Long articleId, SearchVo searchVo){
//        System.out.println(searchVo);
        return commentService.getComments(articleId, searchVo);
    }
}
