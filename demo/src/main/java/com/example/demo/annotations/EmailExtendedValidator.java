package com.example.demo.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.demo.constants.Regex.emailRegexPattern;

public class EmailExtendedValidator implements ConstraintValidator<EmailExtended, String> {
    public boolean isValid(String email, ConstraintValidatorContext cxt) {
        Pattern pattern = Pattern.compile(emailRegexPattern);

        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
