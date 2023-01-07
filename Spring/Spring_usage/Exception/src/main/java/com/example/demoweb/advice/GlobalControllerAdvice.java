package com.example.demoweb.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 예외 처리를 깔끔하게 해줄 수 있는 Annotation
@RestControllerAdvice
public class GlobalControllerAdvice {

    // 요청에 대한 모든 예외를 처리하여 Response 해주는 메소드인 것이다.
    @ExceptionHandler(value = Exception.class) // 모든 예외를 처리
    public ResponseEntity exception(Exception e){ // 예외 매개변수를 받을 수 있다.
        System.out.println(e.getClass().getName());
        System.out.println("Handler : " + e.getLocalizedMessage());

        // 서버 내 에러 (500) 응답.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    // 특정 예외를 지정해줄 수 있다.
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }
}
