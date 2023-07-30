package io.reflectoring.step2_BusinessException;

public class EmailDuplicateException extends InvalidValueException {
    
    public EmailDuplicateException(final Email email) {
        super(email.getValue(), ErrorCode.EMAIL_DUPLICATION);
    }
    
    private class Email {
    
        public String getValue() {
            throw new UnsupportedOperationException("Unsupported getValue");
        }
    }
}
