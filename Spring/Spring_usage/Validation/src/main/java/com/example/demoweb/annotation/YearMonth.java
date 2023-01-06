package com.example.demoweb.annotation;

import com.example.demoweb.validation.YearMonthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// 우리가 Validation 어노테이션(틀)을 만들면 편리하게 적용해서 쓸 수 있다.

@Constraint(validatedBy = { YearMonthValidator.class }) // 검증이 구현된 클래스를 연결시켜주어야 함
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface YearMonth {

    // 에러 메시지 지정
    String message() default "yyyyMM 형식에 맞추십시오.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    // 패턴을 지정할 수 있다.
    String pattern() default "yyyyMMdd"; // 기본 지정
}
