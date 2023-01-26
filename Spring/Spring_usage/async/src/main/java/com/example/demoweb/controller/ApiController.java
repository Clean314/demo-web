package com.example.demoweb.controller;

import com.example.demoweb.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    private final AsyncService asyncService;

    public ApiController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/hello")
    public String hello(){
        asyncService.hello(); // <- 비동기로 동작하는 메소드
        log.info("method end"); // 그래서 위의 메소드가 다 끝날 때까지 기다리지 않아도
        return "hello"; // 클라이언트에게 바로 응답이 가능
    }

    @GetMapping("/hello-completable")
    public CompletableFuture hello_completable(){
        log.info("completable");
        return asyncService.completableFuture();
    }
}
