package com.example.demoweb.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

// 클래스 전체를 Json 에서 사용하는 snakecase 와 맞춰준다.
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CarMember {
    private int memberId;
    private String memberName;

    // Object List 를 property로 사용해보기
    // 물론 Car 클래스의 property 에 대해서 case를 맞춰주려면, Car 클래스에서 따로 설정해야 함.
    private List<Car> carList;

    @Override
    public String toString() {
        return "CarMember{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", carList=" + carList +
                '}';
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
