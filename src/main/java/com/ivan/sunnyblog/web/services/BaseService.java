package com.ivan.sunnyblog.web.services;

import com.ivan.sunnyblog.base.exception.BizException;
import com.ivan.sunnyblog.base.utils.BaseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Author: jinghaoliang
 * Date: 2021-09-30 12:32 p.m.
 **/

public class BaseService extends BaseLogger {

//    public void bizError
    @Autowired
    protected MessageSource ms;

    public void emptyPara(String paraName){
        System.out.println(ms.getMessage("error.param.empty", new String[]{paraName}, Locale.US));
        BizException.throwException(ms.getMessage("error.param.empty", new String[]{paraName}, Locale.US));
    }

    public void bizError(String msgKey){
        BizException.throwException(ms.getMessage(msgKey, null, Locale.US));
    }

//    public String getMessage(String msgKey, Object para) {
//        return ms.getMessage(msgKey, new Object[]{para});
//    }
}
