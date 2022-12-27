package com.example.springstart.di;

import java.util.Base64;

public class Base64Encoder implements EncoderInterface{
    @Override
    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
