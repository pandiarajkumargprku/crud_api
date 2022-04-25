package com.tlc.crm.student.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Email validator to validate the email given by user.
 *
 * @author PandiarajkumarG
 */
public class EmailValidator implements ConstraintValidator<Email, String > {

    /**
     * Checks the validation of input.
     *
     * @param Email
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(final String Email, final ConstraintValidatorContext constraintValidatorContext) {
        return Email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-z]+$") ;
    }
}
