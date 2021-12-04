package com.ivan.sunnyblog.base.exception;

import com.ivan.sunnyblog.web.ResultInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: jinghaoliang
 * Date: 2021-09-28 11:28 p.m.
 **/

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public @ResponseBody Object exceptionHandler(HttpServletResponse response, HttpServletRequest request, Exception e){
        System.out.println("exception handler");
        if(e instanceof BizException){
            response.setStatus(200);
            System.out.println(e.getMessage());
            ResultInfo result = ResultInfo.getErrResult(e.getMessage());
//            result.setInfo(e.getMessage());
            return result;
        }else{
            response.setStatus(500);
            ResultInfo result = ResultInfo.getErrResult("System exception!");
//            result.setInfo("System exception!");
            return result;
        }


    }
}
