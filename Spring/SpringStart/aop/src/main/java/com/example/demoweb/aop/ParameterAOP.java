package com.example.demoweb.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect // dependencies 추가할 때 버전을 붙이지 않아야 작동했다.
@Component // Spring 에서 알아서 관리를 해줘야 함
public class ParameterAOP {

    @Pointcut("execution(* com.example.demoweb.controller..*.*(..))") // 범위 지정
    public void anyNameOK(){}

    // 공통적으로 JoinPoint 파라메터를 받을 수 있다. 호출된 메소드의 정보를 담고있는 객체

    // Pointcut 지정된 범위 내의 모든 메소드가 "실행 전에" 해야 할 것을 작성하면 된다.
    @Before("anyNameOK()")
    public void before(JoinPoint joinPoint){
        System.out.println("--------------------------");
        System.out.println("[Before]");

        // 메소드의 시그니처(리턴타입, 이름, 매개변수) 정보가 저장된 Signature 객체
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("method name : " + methodSignature.getName()); // 이름 얻기

        Object[] args = joinPoint.getArgs(); // 메소드 실행 시 넘겨준 모든 파라메터
        // 각 파라메터에 대해서
        for(Object obj : args){
            System.out.println("name : " + obj.getClass().getSimpleName()); // 간략한 클래스이름 (String, Long 등)
            System.out.println("value : " + obj); // 실제 값
        }

        System.out.println("--------------------------");
    }

    // 실행하고 return 이 된 후
    @AfterReturning(value = "anyNameOK()", returning = "returnObj")
    public void afterReturning(JoinPoint joinPoint, Object returnObj) {
        System.out.println("--------------------------");
        System.out.println("[AfterReturning]");
        // 여기서 받는 Object 파라메터(returnObj)는 pointcut 지정된 메소드의 반환값임
        System.out.println("returnObj : " + returnObj);

        System.out.println("--------------------------");
    }
}
