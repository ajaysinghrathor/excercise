package com.excercise.feed.validation;

import com.excercise.feed.entity.Portfolio;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class DataValidationService {
    private Validator validator;

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    public DataValidationService(){
        this.validator = factory.getValidator();
    }

    public boolean validate(Portfolio portfolio){
        Set<ConstraintViolation<Portfolio>> violations = validator.validate(portfolio);
        if(violations.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
