package com.example.springstart.di;

public class Main {
    public static void main(String[] args) {
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        // Base64 Encoder와 UrlEncoder를 자유롭게 주입해서 사용하면 된다.
        Encoder encoder = new Encoder(new Base64Encoder());
        //Encoder encoder = new Encoder(new UrlEncoder());
        System.out.println(encoder.encode(url));
    }
}
