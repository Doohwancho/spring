package io.reflectoring.step1_Exception_enum방식;


public class ErrorUtils {

  private ErrorUtils() {
  }

  public static Error createError(final String errMsgKey, final String errorCode,
      final Integer httpStatusCode) {
    Error error = new Error();
    error.setMessage(errMsgKey);
    error.setErrorCode(errorCode);
    error.setStatus(httpStatusCode);
    return error;
  }
}

