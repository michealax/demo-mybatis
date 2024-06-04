package com.shane.mybatis.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CardNumberValidator implements ConstraintValidator<CardNumber, String> {
    private static final String REGEX_TEL = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return Pattern.matches(REGEX_TEL, s);
        } catch (Exception e) {
            return false;
        }
    }
}
