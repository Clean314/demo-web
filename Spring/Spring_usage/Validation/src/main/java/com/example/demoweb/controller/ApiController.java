package com.example.demoweb.controller;

import com.example.demoweb.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("post")
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult){ // @Valid 사용해야 함
        
        // 바로 리턴되지 않고 binding 에 담을 수 있음
        if (bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder(); // 에러 내용을 모두 담기 위함.

            bindingResult.getAllErrors().forEach(objectError -> { // 각 모든 에러마다
                FieldError fieldError = (FieldError) objectError; // 필드 에러로 담기
                String message = objectError.getDefaultMessage(); // 에러 메시지 담기

//                System.out.println("field : " + fieldError.getField()); // 어느 필드인지
//                System.out.println("message : " + message); // 메시지는 무엇인지

                sb.append("\nfield : " + fieldError.getField());
                sb.append("\nmessage : " + fieldError.getDefaultMessage());
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
        return ResponseEntity.ok(user);
    }
}
