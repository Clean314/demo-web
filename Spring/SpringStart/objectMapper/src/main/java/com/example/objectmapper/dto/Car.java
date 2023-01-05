package com.example.objectmapper.dto;

public class Car {
    private String carName;
    private String carNumber;
    private String TYPE;

    @Override
    public String toString() {
        return "Car{" +
                "carName='" + carName + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", TYPE='" + TYPE + '\'' +
                '}';
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
