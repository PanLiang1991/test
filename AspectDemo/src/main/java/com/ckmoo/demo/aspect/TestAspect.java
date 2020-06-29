package com.ckmoo.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

    private static final Logger logger = LoggerFactory.getLogger(OnlyTest.class);

    @Pointcut("@annotation(com.ckmoo.demo.aspect.OnlyTest)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object preHandler(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        try {
            String className = joinPoint.getTarget().getClass().getName();
            System.out.println("开始打印初始日志！");
            logger.info(String.format("Method [%s.%s] start", className, joinPoint.getSignature().getName()));
            obj = joinPoint.proceed();
            System.out.println("开始打印结束日止啦！");
            logger.info(String.format("Method [%s.%s] end", className, joinPoint.getSignature().getName()));
        } catch (Throwable throwable) {
            logger.error("occur serious bug!");
        }
        return obj;
    }
}
