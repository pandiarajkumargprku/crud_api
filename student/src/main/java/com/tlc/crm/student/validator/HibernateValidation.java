package com.tlc.crm.student.validator;

import com.tlc.commons.code.ErrorCode;
import com.tlc.crm.student.api.StudentDetails;
import com.tlc.crm.student.status.StudentErrorCode;
import com.tlc.validator.ModelValidator;
import com.tlc.validator.ValidatorAccess;

/**
 * Hibernate Validation to validate the given input.
 *
 * @author PandiarajkumarG
 */
public class HibernateValidation {

    private static final ModelValidator MODEL_VALIDATOR = ValidatorAccess.get();

    /**
     * Validates the student data.
     *
     * @param student
     * @param clazz
     */
    public static void validate(final StudentDetails student, final Class... clazz) {

        if (!MODEL_VALIDATOR.isValid(student, clazz)) {
            throw ErrorCode.get(StudentErrorCode.VALIDATE_FAILED);
        }
    }
}
