package com.example.demoweb.controller;

import com.example.demoweb.annotation.Timer;
import com.example.demoweb.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping("get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        System.out.println("id : "+ id);
        System.out.println("name : "+ name);
        return id + ", " + name;
    }

    @PostMapping("post")
    public User post(@RequestBody User user){
        System.out.println("value body : " + user);
        return user;
    }

    @Timer
    @DeleteMapping("delete")
    public void delete() throws InterruptedException {
        Thread.sleep(1000*2);
    }
}
