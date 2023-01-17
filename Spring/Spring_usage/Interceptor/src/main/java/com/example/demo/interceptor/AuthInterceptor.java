package com.example.demo.interceptor;

import com.example.demo.annotation.Auth;
import com.example.demo.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    // 기본적으로 모든 요청에 대하여 동작한다.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        log.info("url : {}", url);

        // uri 형태의 String을 가져와서 적합한 URI 클래스 형태로 변환
        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build().toUri();

        // annotation 존재 확인
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("annotation : {}", hasAnnotation);

        // 기본적으로 public 으로 동작하지만 Auth 에 대해서는,
        // 즉, 권한을 가지고 있는 요청에 대해서는 쿠키나 세션 등을 검사하게 한다.
        if(hasAnnotation){
            String query = uri.getQuery(); // 쿼리 값 가져오기
            if(query.equals("name=steve")){ // 쿼리의 name 이 steve 일 때만 true 이다.
                return true;
            }
            // 예외 처리. AuthException 은 개발자가 만든 예외 클래스
            throw new AuthException();
        }

        return true;
    }

    // Annotation 이 있는지 확인
    private boolean checkAnnotation(Object handler, Class clazz){

        // Resource 에 대한 요청일 때는 모두 pass
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        // Annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            // Auth annotation 이 있는 경우임
            return true;
        }

        return false;
    }
}
