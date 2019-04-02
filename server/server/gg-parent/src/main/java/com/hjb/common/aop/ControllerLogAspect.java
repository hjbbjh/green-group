package com.hjb.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: UserMain
 * Description:
 * Created by hjb on 2019/3/29 9:26
 */
@Aspect
@Component
public class ControllerLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("execution(public * com.hjb.controller..*.*(..))")
    public void controllerLog(){}


    /*
    * Description controller log around aop
    * Created by hjb on 2019/3/29 10:10
    * Param [joinPoint]
    * return java.lang.Object
    **/
    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();

        Long startTime = System.nanoTime();
        Object obj;
        try {
            obj = joinPoint.proceed();
        }catch (Throwable th){
            th.printStackTrace();
            logger.error("{} 执行controller方法：{},发生异常 {}",httpServletRequest.getRequestURI(),joinPoint.getSignature().toString(),th.getMessage());
            throw th;
        }
        Long endTime = System.nanoTime();

        logger.info("{} 执行controller方法：{}，耗时 {} 纳秒，耗时 {} 毫秒",httpServletRequest.getRequestURI(),joinPoint.getSignature().toString(),endTime-startTime,(endTime-startTime)/1000000);
        return obj;
    }
}
