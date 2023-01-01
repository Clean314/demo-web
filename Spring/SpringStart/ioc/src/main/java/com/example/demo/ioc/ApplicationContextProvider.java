package com.example.demo.ioc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    // ApplicationContext 를 Provider 한다.
    private static ApplicationContext applicationContext;

    // 아래는 getter 와 setter 이겠지.

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getContext(){
        return applicationContext;
    }
}