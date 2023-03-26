package org.example.com.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogin {

    public enum Type {
        login,
        refresh
    }
    private Type type;
    private String username;
    private String password;
    private String refreshToken;
}
