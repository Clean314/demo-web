package com.example.demo.ioc;

import org.springframework.beans.factory.annotation.Qualifier;

public class MultiEncoder {
    private EncoderInterface encoderInterface;

    public MultiEncoder(EncoderInterface encoderInterface){
        this.encoderInterface = encoderInterface;
    }

    public void setEncoderInterface(EncoderInterface encoderInterface) {
        this.encoderInterface = encoderInterface;
    }

    public String encode(String message){
        return encoderInterface.encode(message);
    }
}
