package com.example.demoweb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {
    public String hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server")
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString());

        // 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        // uri 로 요청을 보내고, response 타입을 정해주어야 한다.
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }
}
