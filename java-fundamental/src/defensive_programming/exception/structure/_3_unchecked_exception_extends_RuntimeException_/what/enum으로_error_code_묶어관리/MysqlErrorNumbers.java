package defensive_programming.exception.structure._3_unchecked_exception_extends_RuntimeException_.what.enum으로_error_code_묶어관리;

enum MysqlErrorNumbers {
    ER_DUP_USER_ID(12439), ER_OTHER_USER_PROBLEM(22439);
    
    private final int errorCode;
    
    MysqlErrorNumbers(int errorCode) {
        this.errorCode = errorCode;
    }
    
    public int getErrorCode() {
        return errorCode;
    }
}
