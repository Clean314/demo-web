package com.example.demoweb.controller;

import com.example.demoweb.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/ex-api")
@Validated // Controller 에서 파라메터를 검증할 수 있는 Annotation
public class ExApiController {

    @GetMapping("/get")
    public User get(
            @Size(min = 1) // 조건
            @RequestParam(required = false) String name,

            @NotNull()
            @RequestParam(required = false) Integer age){
        // required false 를 하면 쿼리값이 빈 요청을 받을 수 있게 됨
        // User 프로퍼티들이 비어선 안되는 것으로 지정하여 의도적인 에러 발생

        User user = new User();
        user.setName(name);
        user.setAge(age);
        int a = age + 10; // NullPointerException 발생될 것임

        return user;
    }

    @PostMapping("/post")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }

    // 이 컨트롤러 내부에 대해서만 처리하고, 가장 우선된다.
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println("ApiController Exception");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
