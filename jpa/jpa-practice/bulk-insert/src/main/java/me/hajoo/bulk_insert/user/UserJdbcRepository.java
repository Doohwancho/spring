package me.hajoo.bulk_insert.user;

import lombok.RequiredArgsConstructor;
import me.hajoo.bulk_insert.user.User;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAll(List<User> users) {
        jdbcTemplate.batchUpdate("insert into users(email, password, name) values(?, ?, ?)"
                , new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, users.get(i).email);
                        ps.setString(2, users.get(i).password);
                        ps.setString(3, users.get(i).name);
                    }

                    @Override
                    public int getBatchSize() {
                        return users.size();
                    }
                });

    }
}
