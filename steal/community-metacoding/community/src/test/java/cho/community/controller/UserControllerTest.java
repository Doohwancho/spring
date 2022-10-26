package cho.community.controller;


import cho.community.controller.user.UserController;
import cho.community.dto.user.UserDto;
import cho.community.entity.user.User;
import cho.community.repository.user.UserRepository;
import cho.community.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static cho.community.factory.UserFactory.createUserWithAdminRole;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserRepository userRepository;

    @Mock
    UserService userService;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("전체 회원 조회")
    public void findAllUsersTest() throws Exception {
        mockMvc.perform(
                        get("/api/users"))
                .andExpect(status().isOk());
        verify(userService).findAllUsers();
    }

    @Test
    @DisplayName("개별 회원 조회")
    public void findUserTest() throws Exception {
        //given
        int id = 1;

        //when, then
        mockMvc.perform(
                        get("/api/users/{id}", id))
                .andExpect(status().isOk());
        verify(userService).findUser(id);
    }

//    @Test
//    @DisplayName("즐겨찾기 조회")
//    public void findFavoritesTest() throws Exception {
//        // given
//        User user = createUserWithAdminRole();
//
//        // when, then
//        mockMvc.perform(
//                get("/api/users/favorites"))
//                .andExpect(status().isOk());
//        verify(userService).findFavorites(user);
//    }

    @Test
    @DisplayName("회원 정보 수정")
    public void editUserInfoTest() throws Exception {
        // given
        int id = 1;
        UserDto userDto = new UserDto(1, "username", "name", "nickname");

        // when, then
        mockMvc.perform(
                        put("/api/users/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());

        verify(userService).editUserInfo(id, userDto);
        assertThat(userDto.getName()).isEqualTo("name");

    }

    @Test
    @DisplayName("회원 탈퇴")
    public void deleteUserInfoTest() throws Exception {
        // given
        int id = 1;

        User user = createUserWithAdminRole();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getId(), "", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        given(userRepository.findByUsername(authentication.getName())).willReturn(Optional.of(user));

        // when then
        mockMvc.perform(
                        delete("/api/users/{id}", id))
                .andExpect(status().isOk());

        verify(userService).deleteUserInfo(user, id);

    }
}

