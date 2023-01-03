package com.example.demoweb.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAOP {
    @Pointcut("execution(* com.example.demoweb.controller..*.*(..))")
    public void cut() {}

    @Pointcut("@annotation(com.example.demoweb.annotation.Timer)")
    private void enableTimer(){}

    @Around("cut() && enableTimer()") // 적용 범위의 조건을 주는 것
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object object = proceedingJoinPoint.proceed(); // 실제 실행 부분

        stopWatch.stop();

        System.out.println("time : " + stopWatch.getTotalTimeSeconds());
    }
}
