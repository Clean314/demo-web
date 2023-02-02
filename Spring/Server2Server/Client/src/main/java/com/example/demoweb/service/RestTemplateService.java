package com.example.demoweb.service;

import com.example.demoweb.dto.Req;
import com.example.demoweb.dto.UserRequest;
import com.example.demoweb.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
        // getForEntity 는 get에 대한 처리이다.
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        
        // 응답받고 출력해보기
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

        // post 처리 : postForEntity
        // uri, 보낼 거, 받을 형식
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

        // 헤더를 지정할 수 있다.
        // RequestEntity 에 헤더 지정하고 uri 정보도 담는다.
        RequestEntity<UserRequest> userRequestRequestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "Abcd")
                .header("custom-header", "aaaa")
                .body(userRequest);

        // exchange(RequestEntity, 응답받을 형식)
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> userResponseResponseEntity
                = restTemplate.exchange(userRequestRequestEntity, UserResponse.class);

        return userResponseResponseEntity.getBody();
    }

    public Req<UserResponse> genericExchange() {
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

        Req<UserRequest> req = new Req<>();
        req.setHeader(new Req.Header());
        req.setResBody(userRequest);

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "Abcd")
                .header("custom-header", "aaaa")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Req<UserResponse>> responseEntity = restTemplate.exchange(
            requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){} // .Class가 안되기 때문
        );

        return responseEntity.getBody();
    }
}
