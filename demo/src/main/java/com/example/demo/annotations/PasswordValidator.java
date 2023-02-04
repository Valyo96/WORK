package com.example.demo.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.demo.constants.Regex.passwordRegexPattern;

public class PasswordValidator implements ConstraintValidator<Password , String> {
    public boolean isValid(String password, ConstraintValidatorContext cxt) {
        Pattern pattern = Pattern.compile(passwordRegexPattern);

        if (password == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
