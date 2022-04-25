package com.tlc.crm.student.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface Email {
    String message() default "INVALID_EMAIl";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
