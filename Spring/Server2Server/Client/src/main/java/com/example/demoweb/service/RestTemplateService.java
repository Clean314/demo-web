package com.example.demoweb.service;

import com.example.demoweb.dto.UserRequest;
import com.example.demoweb.dto.UserResponse;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {
    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString());

        // 요청 보내기
        RestTemplate restTemplate = new RestTemplate();

        // uri 로 요청을 보내고, response 로 받을 타입을 정해주어야 한다.
        // ResponseEntity 를 사용하면 status 코드 등 여러 정보를 함께 담을 수 있다.
        // Class 형태만 바꾸면 된다.
        // getForEntity는 get에 대한 처리이다.
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public UserResponse user(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(10, "steve")
                .toUri();
        System.out.println(uri.toString());

        // http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest userRequest = new UserRequest();
        userRequest.setId(10);
        userRequest.setName("steve");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> userResponseResponseEntity = restTemplate.postForEntity(uri, userRequest, UserResponse.class);

        System.out.println(userResponseResponseEntity.getStatusCode());
        System.out.println(userResponseResponseEntity.getHeaders());
        System.out.println(userResponseResponseEntity.getBody());

        return userResponseResponseEntity.getBody();
    }

    public UserResponse exchange() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(10, "steve")
                .toUri();
        System.out.println(uri.toString());

        // http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest userRequest = new UserRequest();
        userRequest.setId(10);
        userRequest.setName("steve");

        RequestEntity<UserRequest> userRequestRequestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "Abcd")
                .header("custom-header", "aaaa")
                .body(userRequest);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> userResponseResponseEntity
                = restTemplate.exchange(userRequestRequestEntity, UserResponse.class);

        return userResponseResponseEntity.getBody();
    }
}
