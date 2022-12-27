package com.example.springstart.di;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlEncoder implements EncoderInterface{
    @Override
    public String encode(String message) {
        try {
            return URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
