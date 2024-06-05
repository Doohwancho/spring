package _7_defensive_programming.exception.structure._3_unchecked_exception_extends_RuntimeException_.what.enum으로_error_code_묶어관리;

import java.sql.SQLException;

public class UserRepository {
    
    public User addUser(User user) throws SQLException {
        return user;
    }
}
