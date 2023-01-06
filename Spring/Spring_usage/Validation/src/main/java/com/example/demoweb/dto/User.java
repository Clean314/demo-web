package com.example.demoweb.dto;

import com.example.demoweb.annotation.YearMonth;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.format.datetime.joda.LocalDateParser;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

// 각 Annotation 별로 Validation 을 수행한다.
public class User {
    @NotBlank // 공백 X
    private String name;

    @Min(value = 0) // 최솟값
    private Integer age;

    @Email
    private String email;

    // 메시지를 사용자 친화적으로
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 양식을 확인하십시오.")
    private String phoneNumber;
    
    @YearMonth // 적용만 하면 끝. 여러 곳에서 재사용 가능
    private String reqYearMonth;

    @Valid // 하위 클래스에 Validation 만들었더라도 어노테이션 지정을 해야 동작을 함.
    private List<Car> carList;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqYearMonth='" + reqYearMonth + '\'' +
                ", carList=" + carList +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReqYearMonth() {
        return reqYearMonth;
    }

    public void setReqYearMonth(String reqYearMonth) {
        this.reqYearMonth = reqYearMonth;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
