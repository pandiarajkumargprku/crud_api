package com.tlc.crm.student.status;

import com.tlc.commons.code.ErrorCodeGroup;
import com.tlc.commons.code.ErrorCodeProvider;

/**
 * Error code for student data.
 *
 * @author PandiarajkumarG
 */
public enum StudentErrorCode implements ErrorCodeProvider {

    ID_NOT_FOUND(0x01),
    VALIDATE_FAILED(0x02);

    private final int code;

    StudentErrorCode(int code) {
        this.code = StudentErrorCodeGroup.ERROR_CODE_GROUP.getConvertedCode(code);
    }

    /**
     * Gets code
     *
     * @return
     */
    @Override
    public int getCode() {
        return code;
    }

    /**
     * Implements error code group
     */
    private static class StudentErrorCodeGroup implements ErrorCodeGroup {
        private static final ErrorCodeGroup ERROR_CODE_GROUP = new StudentErrorCodeGroup();

        @Override
        public int getPrefix() {
            return 0x10_0_0000;
        }
    }
}
