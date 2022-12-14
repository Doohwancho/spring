package cho.community.factory;

import cho.community.entity.user.Authority;
import cho.community.entity.user.User;

import cho.community.entity.user.Authority;

public class UserFactory {

    public static User createUserWithAdminRole() {
        User user = new User("username", "password", Authority.ROLE_ADMIN);
        user.setName("yoon");
        user.setNickname("yoon");
        return user;
    }

    public static User createUser() {
        User user = new User("username", "password", Authority.ROLE_ADMIN);
        user.setName("yoon");
        user.setNickname("yoon");
        return user;
    }

    public static User createUser2() {
        User user = new User("username2", "password2", Authority.ROLE_ADMIN);
        user.setName("yoon2");
        user.setNickname("yoon2");
        return user;
    }
}
