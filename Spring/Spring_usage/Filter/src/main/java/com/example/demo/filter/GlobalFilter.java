package com.example.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
//@Component // 메소드가 Spring에 bean 으로서 등록되어야 함
@WebFilter(urlPatterns = "/api/user/*") // Filter를 적용할 url 범위를 지정할 수 있다. 배열로 하면 여러가지 넣을 수 있다.
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 전처리

        // ContentCachingRequest/ResponseWrapper 는 읽었던 내용을 캐싱하기 때문에 몇 번이고 읽을 수 있다.
        // ServletRequest/Response 를 HttpServletRequest/Response 로 변환할 수 있다.
        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        // ------ [처리] ------
        chain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);
        // -------------------

        // 후처리
        // 전처리에서는 cachedContent 길이만 지정함. Spring이 내부적으로 매핑을 끝마친 후인 후처리에서 읽어야 에러 X

        // uri 가져오기
        String url = contentCachingRequestWrapper.getRequestURI();

        // 요청 body 가져오기
        String requestContent = new String(contentCachingRequestWrapper.getContentAsByteArray()); // default : UTF-8
        log.info("request url : {}, request body : {}", url, requestContent);

        // 응답 body 가져오기
        String responseContent = new String(contentCachingResponseWrapper.getContentAsByteArray());
        int httpStatus = contentCachingResponseWrapper.getStatus();
        contentCachingResponseWrapper.copyBodyToResponse(); // contentBody 를 다 읽어서 responseContent 를 쓰려면 되돌려 주는 메소드를 쓰면 됨
        // -> contentCaching 에서 제공하는 좋은 기능임. HttpServletRequest 는 아마도 불가
        log.info("response status : {}, response body : {}", httpStatus, responseContent);

        // HttpServletRequest 로 읽는다면 이미 다 읽어서 커서를 되돌릴 수 없을 것임.
//        BufferedReader bufferedReader = contentCachingRequestWrapper.getReader();
//        bufferedReader.lines().forEach(lines -> {
//            log.info("url : {}, lines : {}", url, lines);
//        });

    }
}
