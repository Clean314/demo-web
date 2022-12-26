package com.example.demoweb.controller;

import com.example.demoweb.dto.ResponseUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("html")
    public String html(){
        return "main.html";
    }

    @ResponseBody
    @GetMapping("response-body")
    public ResponseUser responseBody(){
        var responseUser = new ResponseUser();
        responseUser.setName("name");
        //responseUser.setAge(10);
        return responseUser;
    }
}
