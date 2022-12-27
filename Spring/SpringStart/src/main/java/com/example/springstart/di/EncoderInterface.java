package com.example.springstart.di;

public interface EncoderInterface {
    // 공통되는 기능을 인터페이스를 이용하여, 메소드로서 묶는다.
    public String encode(String message);
}
