package com.tlc.crm.student.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
@Documented
public @interface Name {
    String message() default "INVALID_NAME";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
