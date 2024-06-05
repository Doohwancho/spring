package _7_defensive_programming.exception.structure._2_checked_exception_extends_Exception.how.SQLException.wrap_low_level_exceptions;

public class HighLevelException extends RuntimeException {

    HighLevelException(String msg){
        super(msg);
    }
    
    HighLevelException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
