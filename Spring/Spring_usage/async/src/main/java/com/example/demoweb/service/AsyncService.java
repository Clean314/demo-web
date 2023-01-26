package com.example.demoweb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {

    @Async("async-thread") // 커스텀 설정 Bean 불러오기
    public CompletableFuture completableFuture(){
        // CompletableFuture 는 다른 여러 API 로 요청을 받아 조인한 후에 응답해야 하는 경우 쓸 수 있다고 함.
        return new AsyncResult(hello_completable()).completable();
    }

    public String hello_completable(){
        for(int i = 0; i < 10; i++){
            try {
                Thread.sleep(2000);
                log.info("thread sleep");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return "hello completable";
    }

    @Async
    public void hello() {
        for(int i = 0; i < 10; i++){
            try {
                Thread.sleep(2000);
                log.info("thread sleep");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
