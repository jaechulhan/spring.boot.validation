package net.prolancer.validation.common.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;

@Slf4j
public class CheckDateValidator implements ConstraintValidator<CheckDateFormat, String> {

    private String pattern;

    @Override
    public void initialize(CheckDateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if (object == null) {
            return true;
        }

        try {
            new SimpleDateFormat(pattern).parse(object);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
