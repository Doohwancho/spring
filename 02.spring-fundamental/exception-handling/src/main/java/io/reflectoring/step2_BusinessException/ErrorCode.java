package io.reflectoring.step2_BusinessException;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
    ---
    structure of business exceptions
    
    BusinessException extends RuntimeException extends Exception
    1. InvalidValueException extends BusinessException
    2. EntityNotFoundException extends BusinessException
    
    CouponAlreadyUsedException, CouponExpiredException extends InvalidValueException
    MemberNotFoundException, CouponNotFoundException, EmailNotFoundException extends EntityNotFoundException
    
    
    
    ---
    pros
    
    
    1. 일반 runtime exception들과 business exception을 분리 가능
    2. exception을 카테고리 별로(ex. InvalidValueException, EntityNotFoundException 등)으로 구분 가능
    3. logging 등 특정 exception들을 공통처리 가능
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    
    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
    
    
    // Member
    EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
    LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),
    
    // Coupon
    COUPON_ALREADY_USE(400, "CO001", "Coupon was already used"),
    COUPON_EXPIRE(400, "CO002", "Coupon was already expired");
    private final String code;
    private final String message;
    private int status;
    
    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String getCode() {
        return code;
    }
    
    public int getStatus() {
        return status;
    }
    
    
}
