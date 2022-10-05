package com.cho.security.app.user.dto;

import java.util.List;

import com.cho.security.app.user.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserListResponseDTO {

    private final List<User> userList;

}
