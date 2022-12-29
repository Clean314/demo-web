package com.example.demo.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Base64Encoder implements EncoderInterface{
    @Override
    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
