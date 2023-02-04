package com.example.demo.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.demo.constants.ExceptionMessages.emailValidationMessage;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;



@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailExtendedValidator.class)
public @interface EmailExtended {
    String message() default emailValidationMessage;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


