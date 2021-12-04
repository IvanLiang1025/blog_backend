package com.ivan.sunnyblog.web.controller;

import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.services.BlogUserService;
import com.ivan.sunnyblog.web.services.iservice.IBlogUserService;
import com.ivan.sunnyblog.web.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: jinghaoliang
 **/

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private IBlogUserService blogUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultInfo login(@RequestBody LoginVo loginVo){


        ResultInfo result = blogUserService.login(loginVo);
        return result;
    }
}
