package com.ivan.sunnyblog.base.exception;

/**
 * Author: jinghaoliang
 * Date: 2021-09-28 4:20 p.m.
 **/

public class BizException extends RuntimeException{

    public BizException() { }


    public BizException(String message) {
        super(message);
    }


    public BizException(String message, Throwable cause) {
        super(message, cause);
    }


    public BizException(Throwable cause) {
        super(cause);
    }


    protected BizException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static BizException throwException(String message){
        throw new BizException(message);
    }
}
