package com.example.projectwebjava.constraint;

import com.example.projectwebjava.validator.LoginConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {LoginConstraintValidator.class})
@Target({ FIELD, ANNOTATION_TYPE, ElementType.LOCAL_VARIABLE})
@Retention(RUNTIME)
public @interface ValidLogin {
    String message() default "Nieprawidłowy login.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}