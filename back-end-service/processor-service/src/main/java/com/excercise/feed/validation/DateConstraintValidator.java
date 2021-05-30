package com.excercise.feed.validation;

import com.excercise.feed.entity.Portfolio;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateConstraintValidator implements ConstraintValidator<DateValidator, String> {

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        if(date.length() == 11){
            return true;
        }
        return false;
    }
}
