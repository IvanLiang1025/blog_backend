package com.ivan.sunnyblog.web.permission;

import com.ivan.sunnyblog.base.exception.BizException;
import com.ivan.sunnyblog.web.constant.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static com.ivan.sunnyblog.web.constant.GlobalConstant.ACCESS_TOKEN;

/**
 * Author: jinghaoliang
 **/

public class PermissionManager {

    public static Logger logger = LoggerFactory.getLogger(PermissionManager.class);
    public static final String START_ADMIN = "/api/admin/";

    public String checkPermission(HttpServletRequest request){
        String path = request.getRequestURI();
        String accessToken= request.getHeader(GlobalConstant.ACCESS_TOKEN);

        boolean isAdminReq = path.startsWith(START_ADMIN);


        if(isAdminReq) {
            if (accessToken == null) {
                logger.error("*=RQ=*f {}", path);
                BizException.throwException("Please re-login");
//                ForbiddenException.throwException("Forbidden.");
            }

        }
        return null;
    }

}
