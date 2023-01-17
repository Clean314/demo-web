package com.example.demo.config;

import com.example.demo.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Interceptor 에 대한 설정을 위함
@RequiredArgsConstructor // arguments(AuthInterceptor) 를 받는 생성자를 만들어준다.
public class MvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 를 하면 해당 url 패턴에 대해서만 interceptor 를 동작시킬 수 있다
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");
        /*  주의
            api/private/* 로 하니까 안됐었음.
        */
    }
}
