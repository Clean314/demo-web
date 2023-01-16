package com.example.demo.controller;

import com.example.demo.annotation.Auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 권한이 필요한 컨트롤러
@RestController
@RequestMapping("/api/private")
@Auth //
public class PrivateController {

    @GetMapping("/hello")
    public String hello(){
        return "public hello";
    }
}
