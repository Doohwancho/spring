package com.sp.sec.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccessWithAuthorityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    UserDetails 유저() {
        return User.builder()
                .username("user")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();
    }

    UserDetails 운영자() {
        return User.builder()
                .username("admin")
                .password(passwordEncoder.encode("1234"))
                .roles("ADMIN")
                .build();
    }


    @Test
    @DisplayName("일반 사용자는 로그인 페이지에 접근할 수 있다.")
    void anonymousCanAccessLoginPage() throws Exception {
        mockMvc.perform(get("/member/login")).andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("일반 사용자는 유저권한 페이지에 접근할 수 없다..")
    void anonymousCannotAccessUserPage() throws Exception {
        mockMvc.perform(get("/member/user"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * TODO j-a-3
     *
     * 401 - Unauthorized
     * This error indicates that the request sent to the server did not contain the correct authentication credentials.
     * ex. The client may need to provide a valid username and password in order to access the requested resource.
     *
     *
     * 403 - Forbidden
     * This error indicates that the server understood the request, but is refusing to fulfill it.
     * This is often due to the server not having the appropriate permissions to access the requested resource.
     */
    @Test
//    @WithMockUser(username = "user", roles = "USER") //TODO j-a-2 - 권한있는 유저를 Principal에 등록하려면, 1. SecurityConfig.java에서 InmemoryUserDetails에 넣고, username으로 땡겨와서 쓰거나, 2. 아래와 같은 방식 쓰거나.
    @DisplayName("유저는 운영자 페이지에 접근할 수 없다.")
    void userCannotAccessToAdminPage() throws Exception {
        mockMvc.perform(get("/member/admin").with(user(유저())))
                .andExpect(status().isForbidden());
    }

}
