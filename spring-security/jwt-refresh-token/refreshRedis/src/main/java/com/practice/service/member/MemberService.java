package com.practice.service.member;


import com.practice.domain.Member;
import com.practice.dto.JwtTokenDto;
import com.practice.dto.MemberDto;
import com.practice.dto.LoginDto;
import com.practice.dto.JoinDto;

import java.security.Principal;

public interface MemberService {
    JwtTokenDto login(LoginDto loginModel);

    Long register(JoinDto memberModel);

    Long registerAdmin(JoinDto memberModel);

    Member authenticate(LoginDto loginModel);

    void logout(String accessToken, String username);

    JwtTokenDto reissue(String refreshToken, Principal principal);

    MemberDto findMemberById(String username);
}
