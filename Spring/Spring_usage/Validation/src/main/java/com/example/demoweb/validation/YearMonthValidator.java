package com.example.demoweb.validation;

import com.example.demoweb.annotation.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// 어노테이션(틀)을 이용하여 구체적인 메소드는 여기에 작성한다.
public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    // 지정한 패턴을 가져오도록 한다.
    private String pattern;

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern(); // 초기화 시에 패턴을 가져옴
    }

    // 진짜 확인 메소드
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try{
            // 사용자가 yyyyMM 까지 넣는다고 생각함. 로직에서의 패턴은 임의로 "01"을 붙여 yyyyMMdd 로 검사해서 처리한다.
            LocalDate localDate =
                    LocalDate.parse(value + "01", DateTimeFormatter.ofPattern(this.pattern));
        }catch(Exception e){
            return false;
        }
        return true;
    }

}
