package com.example.objectmapper;

import com.example.objectmapper.dto.Car;
import com.example.objectmapper.dto.User;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("userName");
        user.setAge(10);

        Car car1 = new Car();
        car1.setCarName("car1");
        car1.setCarNumber("11");
        car1.setTYPE("A");

        Car car2 = new Car();
        car2.setCarName("car1");
        car2.setCarNumber("22");
        car2.setTYPE("B");

        List<Car> carList = Arrays.asList(car1, car2);
        user.setCarList(carList);

        System.out.println(user);

        
    }
}