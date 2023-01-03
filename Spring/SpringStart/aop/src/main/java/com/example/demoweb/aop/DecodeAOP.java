package com.example.demoweb.aop;

import com.example.demoweb.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAOP {
    @Pointcut("execution(* com.example.demoweb.controller..*.*(..))")
    public void cut() {}

    @Pointcut("@annotation(com.example.demoweb.annotation.Decode)")
    private void enableDecode(){}

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();
        for(Object arg : args) {
            if (arg instanceof User) {
                User user = User.class.cast(arg);
                String email = new String(Base64.getDecoder().decode(user.getEmail()), "UTF-8");
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturning(JoinPoint joinPoint, Object returnObj){
        Object[] args = joinPoint.getArgs();
        for(Object arg : args) {
            if (arg instanceof User) {
                User user = User.class.cast(arg);
                String base64Email = Base64.getEncoder().encodeToString(user.getEmail().getBytes());
                user.setEmail(base64Email);
            }
        }
    }
}
