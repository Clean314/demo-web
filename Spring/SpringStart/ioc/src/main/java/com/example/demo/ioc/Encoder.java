package com.example.demo.ioc;

public class Encoder {
    private EncoderInterface encoderInterface;

    // ** 의존성 주입 **
    // setter 로도 가능하지만 생성자 주입을 Spring 에서 권장한다.
    public Encoder(EncoderInterface encoderInterface){
        this.encoderInterface = encoderInterface;
    }

    public void setEncoderInterface(EncoderInterface encoderInterface) {
        this.encoderInterface = encoderInterface;
    }

    // encoderInterface 객체의 구현 메소드, encode()를 실행하게 한다.
    public String encode(String message){
        return encoderInterface.encode(message);
    }
}
