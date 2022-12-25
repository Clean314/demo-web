package com.example.demoweb.controller;

import com.example.demoweb.dto.CarMember;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/put")
public class PutController {

    // path parameter 와 CarMember 클래스를 함께 받아보기.
    @PutMapping("/sample-put/{id}")
    public String samplePut(@RequestBody CarMember carMember, @PathVariable int id){
        System.out.println("id : "+id);
        return carMember.toString();
    }
}
