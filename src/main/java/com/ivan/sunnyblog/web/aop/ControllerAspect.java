package com.ivan.sunnyblog.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Author: jinghaoliang
 **/
@Aspect
@Component
public class ControllerAspect {

    final static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(* com.ivan.sunnyblog.web.controller.*.*(..))")
    public void controllerPointcut(){}

    @Around("controllerPointcut()")
    public Object around(ProceedingJoinPoint pjt) throws Throwable{
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String requestURI = request.getRequestURI();
//        String requestMethod = request.getMethod();
//        String methodName;
//        methodName = pjt.getSignature().getDeclaringTypeName() + "." + pjt.getSignature().getName();
//        Object[] args = pjt.getArgs();
        Object object = null;
        Long startTime = System.currentTimeMillis();
        Long endTime;
        try{
            object = pjt.proceed();
            endTime = System.currentTimeMillis();

            recordLog(pjt, endTime-startTime, object, true);

//            logger.info("*** Request Success ***: {}ms {} URI={}, methodName={}, args={}, result={}",
//                    endTime-startTime, requestMethod, requestURI, methodName, args, object);
        }catch (Throwable e){
            endTime = System.currentTimeMillis();

            recordLog(pjt, endTime-startTime, null, false);
//            logger.info("*** Request Error ***: {}ms {} URI={}, methodName={}, args={}, result={}",
//                    endTime-startTime, requestMethod, requestURI, methodName, args, object);
            throw e;
        }


        return object;
    }

    public void recordLog(ProceedingJoinPoint pjt, Long processTime, Object result, boolean success){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        String methodName;
        methodName = pjt.getSignature().getDeclaringTypeName() + "." + pjt.getSignature().getName();
        Object[] args = pjt.getArgs();
//        Object object = null;
//        Long startTime = System.currentTimeMillis();
//        Long endTime;
        logger.info("===== log starts =====");
        if(success){
            logger.info("Request succeed");
        }else {
            logger.error("Request failed");
        }
        logger.info("Request Method: {}", requestMethod);
        logger.info("Request URI: {}", requestURI);
        logger.info("Method Name: {}", methodName);
        logger.info("Params: {}", args);
        logger.info("Result: {}", result);
        logger.info("Process Time: {}ms", processTime);
        logger.info("===== log ends =====");
    }


}
