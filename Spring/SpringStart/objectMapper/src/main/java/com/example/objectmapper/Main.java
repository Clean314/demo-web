package com.example.objectmapper;

import com.example.objectmapper.dto.Car;
import com.example.objectmapper.dto.User;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
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

        //System.out.println(user);

        // Write
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        // json 형태의 문자열을 JsonNode 로 담기
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        Integer _age = jsonNode.get("age").asInt();
        System.out.println("name : " + _name);
        System.out.println("age : " + _age);

        // Object 배열을 가져오는 법
        JsonNode cars = jsonNode.get("car_list");
        ArrayNode arrayNode = (ArrayNode) cars;
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>(){});
        System.out.println(_cars);
        
        // ObjectNode 로 값 변환
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "steve");
        objectNode.put("age", 20);

        System.out.println(objectNode.toString());
    }
}