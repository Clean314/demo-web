package com.example.demoweb.controller;

import com.example.demoweb.dto.Req;
import com.example.demoweb.dto.UserResponse;
import com.example.demoweb.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    private final RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello")
    public UserResponse hello(){
        return restTemplateService.hello();
    }

    @GetMapping("/user")
    public UserResponse user(){
        return restTemplateService.user();
    }

    @GetMapping("/exchange")
    public UserResponse exchange(){
        return restTemplateService.exchange();
    }

    @GetMapping("/generic-exchange")
    public Req<UserResponse> genericExchange(){
        return restTemplateService.genericExchange();
    }
}
