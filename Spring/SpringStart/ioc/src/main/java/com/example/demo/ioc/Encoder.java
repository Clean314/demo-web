package com.example.demo.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// Encoder 를 Component 로 등록하면, EncoderInterface 도 자동으로 주입을 받게된다는 뜻이다.
@Component
public class Encoder {
    private EncoderInterface encoderInterface;

    // ** 의존성 주입 **
    // setter 로도 가능하지만 생성자 주입을 Spring 에서 권장한다.

    // url과 base 2개가 bean으로 존재함.
    // 1개가 아니라서 자동으로 주입을 할 수가 없기 때문에, qualifier 필요
    public Encoder(@Qualifier("base64Encoder") EncoderInterface encoderInterface){
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
