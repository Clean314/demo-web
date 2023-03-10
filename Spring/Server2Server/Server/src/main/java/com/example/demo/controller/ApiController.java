package com.example.demo.controller;

import com.example.demo.dto.Req;
import com.example.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ApiController {

    @GetMapping("/hello")
    public User hello(){
        return new User(10, "steve");
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> user(
            @RequestBody Req<User> user,
            @PathVariable int userId,
            @PathVariable String userName,
            @RequestHeader("x-authorization") String auth,
            @RequestHeader("custom-header") String custom){

        log.info("auth : {}, custom : {}", auth, custom);
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("client req : {}", user);

        Req<User> response = new Req<>();
        response.setHeader(new Req.Header());
        response.setResBody(user.getResBody());

        return response;
    }
}