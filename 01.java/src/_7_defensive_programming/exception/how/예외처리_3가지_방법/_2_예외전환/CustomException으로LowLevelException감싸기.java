package _7_defensive_programming.exception.how.예외처리_3가지_방법._2_예외전환;

import java.sql.SQLException;

/*
    low-level exception을 custom high level exception으로 한겹 감싸는 것

    pros
    1. db vendor에서 제공하는 low-level exception이 여러 경우가 퉁쳐졌거나, 의미가 이해하기 어렵게 써져있는 경우, 좀 더 clear한 custom exception으로 감싸서 debugging 때 이해하기 편할 수 있다.

    cons
    1. debugging시 stack trace에 한번 더 타고 가야 함.
    2. 읽고 해석해야 하는 코드의 양이 늘어남

 */

public class CustomException으로LowLevelException감싸기 {
    private UserRepository userRepository;

    public User findUser(String userId) {
        try {
            return userRepository.findUser(userId);
        } catch (SQLException e) { //SQLException을 던졌지만,
            try {
                throw new UserNotFoundException("User with id " + userId + " not found.", e); //custom exception으로 한겹 더 감싼 것.
            } catch (UserNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return new User();
    }

    private class User {

    }

    private class UserRepository {

        public User findUser(String userId) throws SQLException {
            throw new SQLException("sql exception");
        }
    }

    private class UserNotFoundException extends Throwable {

        public UserNotFoundException(String s, SQLException e) {
            super(s);
            e.printStackTrace();
        }
    }
}
