package com.example.demoweb.controller;

import com.example.demoweb.dto.ResponseUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/response")
public class ResponseController {

    // json 을 response 로 주기
    @PostMapping("json")
    public ResponseUser json(@RequestBody ResponseUser responseUser){
        return responseUser;
    }

    // body에 Object를 담아서 상태코드와 함께 주겠다.
    @PutMapping("status")
    public ResponseEntity<ResponseUser> status(@RequestBody ResponseUser responseUser){
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

}
