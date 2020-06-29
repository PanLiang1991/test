package com.muyue.springbootdemo.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.*;

@Aspect
@Component
public class AuthCheckAspect {

    private static Logger logger = LoggerFactory.getLogger(AuthCheckAspect.class);

    @Pointcut("@annotation(com.muyue.springbootdemo.annotation.AuthCheck)")
    public void doOnMethod() {

    }


    @Around("doOnMethod()")
    public void authCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> aClass = joinPoint.getTarget().getClass();
        Signature signature = (MethodSignature)joinPoint.getSignature();
        String name = ((MethodSignature) signature).getMethod().getName();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //使用request对象的getSession()获取session，如果session不存在则创建一个

        HttpSession session = request.getSession();
//将数据存储到session中
        session.setAttribute("username", "muyue");
        session.setAttribute("userid", "12345678");
        session.setMaxInactiveInterval(60 * 20); //单位秒
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie cookie = new Cookie("password","12345678");
        response.addCookie(cookie);
        logger.info("=======" + name);
        response.sendRedirect("/user/getSession");
    }




}
