package com.example.di.nextstep.jwp.stage1;

import java.sql.SQLException;
import java.util.HashMap;

import com.example.di.nextstep.User;
import org.h2.jdbcx.JdbcDataSource;

import java.util.Map;

public class UserDao1 {

    private static final Map<Long, User> users = new HashMap<>();

    private final JdbcDataSource dataSource;

    public UserDao1() {
        final var jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;");
        jdbcDataSource.setUser("");
        jdbcDataSource.setPassword("");

        this.dataSource = jdbcDataSource;
    }

    public void insert(User user) {
        try (final var connection = dataSource.getConnection()) { //dataSource의 connection이 있으면
            users.put(user.getId(), user); //user을 넣어준다.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findById(long id) {
        try (final var connection = dataSource.getConnection()) { //dataSource의 connection이 있으면
            return users.get(id);
        } catch (SQLException e) {
            return null;
        }
    }
}
