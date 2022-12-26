package com.example.demoweb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String name;
    private int age;
    @JsonProperty("phone_number")
    private String phoneNumber; // 이렇게 하면 text일 때는 jsonProperty 가 동작한다. object에서는 그대로.

    // text -> Object 변환 시 필요한 기본 생성자
    public User(){
        this.name = null;
        this.age = 0;
        this.phoneNumber = null;
    }
    public User(String name, int age, String phoneNumber){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // object -> text 변환 시 필수인 getter
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // objectMapper 사용 시, 메소드 이름 앞에 get 쓰지않도록 주의한다.
    // public User getDefaultUser() {}


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
