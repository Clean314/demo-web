package com.example.demo.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Encoder {
    // Encoder 를 Component 로 등록하면, EncoderInterface 도 자동으로 주입을 받게된다는 뜻이다.
    // 그러니 EncoderInterface 자동 주입의 추가적인 설정이 필요함.
    private EncoderInterface encoderInterface;

    // url과 base 2개가 bean으로, 1개가 아니라서 자동 EncoderInterface 주입을 할 수 없으니
    // qualifier 가 필요하다.
    public Encoder(@Qualifier("base64Encoder") EncoderInterface encoderInterface){
        this.encoderInterface = encoderInterface;
    }

    // setter 주입이므로 필요없는 듯함.
    public void setEncoderInterface(EncoderInterface encoderInterface) {
        this.encoderInterface = encoderInterface;
    }

    // encoderInterface 객체의 구현 메소드, encode()를 실행하게 한다.
    public String encode(String message){
        return encoderInterface.encode(message);
    }
}