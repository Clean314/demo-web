package com.example.demoweb.controller;

import com.example.demoweb.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/get")
public class GetController {

    @GetMapping("basic")
    public String basic(){
        return "basic get";
    }

    @GetMapping("path-variable/{value}")
    public String pathVariable(@PathVariable(name = "value") String pathVariable){
        System.out.println("Path value : " + pathVariable);
        return pathVariable;
    }

    @GetMapping("query-parameter")
    public String queryParameter(@RequestParam String name, @RequestParam int age){
        System.out.println("Name : " + name);
        System.out.println("Age : " + age);

        return name + " " + age;
    }

    @GetMapping("query-parameter-dto")
    public String queryParameterDTO(User user){
        return user.toString();
    }
}
