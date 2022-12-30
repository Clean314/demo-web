package com.example.demo.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

// @Component("base74Encoder") 이름을 다르게 지정해줄 수 있다.
// 따로 지정하지 않으면 클래스 이름의 앞글자만 소문자로 바꾼 이름이 사용된다.
@Component
public class Base64Encoder implements EncoderInterface{
    @Override
    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
