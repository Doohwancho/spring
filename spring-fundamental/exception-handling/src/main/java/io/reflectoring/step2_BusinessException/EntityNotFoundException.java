package io.reflectoring.step2_BusinessException;

public class EntityNotFoundException extends BusinessException {
    
    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
}
