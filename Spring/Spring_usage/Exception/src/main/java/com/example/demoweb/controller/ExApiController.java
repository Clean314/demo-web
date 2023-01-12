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
            @Size(min = 2) // 조건
            @RequestParam String name,

            @NotNull()
            @Min(value = 1)
            @RequestParam Integer age){

        User user = new User();
        user.setName(name);
        user.setAge(age);

        return user;
    }

    @PostMapping("/post")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }


}
