package com.example.demo.annotation;

import java.lang.annotation.*;

// Annotation 구성에 대해서 따로 공부할 것
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Auth {
}