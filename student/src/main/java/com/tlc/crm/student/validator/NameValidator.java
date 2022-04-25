package com.tlc.crm.student.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Name validator to validate the name given by the user.
 *
 * @author PandiarajkumarG
 */
public class NameValidator implements ConstraintValidator<Name, String> {

    /**
     * Checks the validation of input.
     *
     * @param Name
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(final String Name, final ConstraintValidatorContext constraintValidatorContext) {
        return Name.matches("[A-Z][A-Za-z+\s]*$");
    }
}
