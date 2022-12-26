package com.example.demoweb.controller;

import com.example.demoweb.dto.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {


    // 문자열 매핑 방식. 좋지 않음
    @PostMapping("user-map")
    // Post 에는 RequestBody annotation 이 꼭 필요하다.
    public void postMap(@RequestBody Map<String , String> requestBody){
        requestBody.forEach((key, value) -> {
            System.out.println("key : "+ key);
            System.out.println("value : "+ value);
        });
    }

    // DTO 로 매핑. 물론 Getter&Setter 가 구현되어있어야 한다
    @PostMapping("user-json")
    public String postJson(@RequestBody User user){
        return user.toString();
    }

}
