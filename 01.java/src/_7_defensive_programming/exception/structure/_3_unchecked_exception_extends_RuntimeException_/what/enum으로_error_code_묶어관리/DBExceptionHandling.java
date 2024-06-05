package _7_defensive_programming.exception.structure._3_unchecked_exception_extends_RuntimeException_.what.enum으로_error_code_묶어관리;

import java.sql.SQLException;

/*
    vendor사 마다 있는 수백, 수천개의 sql exceptions들을 SQLException 하나로 퉁쳐있으니까,
    그 안에 error code를 if-else 분기 따서 상세처리하는 방법.

    단, 한계가 있는데,
    vendor사마다, error code 별로 수백개 if-else 추가하는 것도 일이고,
    db 버전 업그레이드 하면, 이 코드도 다 갈아엎어야 한다는 문제점이 있다.
    이거 해결해주는 라이브러리 없나?

    ex. errorcode 17002, 17447는 모두 'dataAccessResourceFailureCodes 에 속하니까, custom Exception 하나로 묶어서 관리
 */

public class DBExceptionHandling {
    
    final UserRepository userRepository;
    
    DBExceptionHandling(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public User add(User user) throws DuplicateUserIdException, OTherUserException, SQLException {
        try {
            return userRepository.addUser(user);
        } catch (SQLException e) { //SQLException은 여러 vendor사마다 다른 sql exceptions들을 하나로 퉁친거라서
            if (e.getErrorCode()
                == MysqlErrorNumbers.ER_DUP_USER_ID.getErrorCode()) { //에러코드마다 분기를 다르게 처리한다.
                throw new DuplicateUserIdException("유저 id가 중복되었습니다.");
            }
            if (e.getErrorCode()
                == MysqlErrorNumbers.ER_OTHER_USER_PROBLEM.getErrorCode()) { //에러코드마다 분기를 다르게 처리한다.
                throw new OTherUserException("유저 insert시 다른 문제가 생겼습니다.");
            }
            throw e; //custom error 처리 아닌 경우, 그냥 SQLException 자체를 던진다.
        }
    }
    
    private class DuplicateUserIdException extends RuntimeException {
        
        public DuplicateUserIdException(String msg) {
            super(msg);
        }
    }
    
    private class OTherUserException extends RuntimeException {
        
        public OTherUserException(String msg) {
            super(msg);
        }
    }
}
