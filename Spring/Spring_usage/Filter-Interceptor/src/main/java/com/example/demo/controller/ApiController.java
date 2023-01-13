package com.example.demo.controller;

import com.example.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j // lombok 에서 제공하는 로그
@RestController
@RequestMapping("/api/user")
public class ApiController {
    
    @PostMapping("/post")
    public User post(@RequestBody User user){
        log.info("User : {}", user); // {} 로 매칭
        return user;
    }
}
