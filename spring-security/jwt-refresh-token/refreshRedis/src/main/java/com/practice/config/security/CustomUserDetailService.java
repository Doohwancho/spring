package com.practice.config.security;

import com.practice.config.redis.cache.CacheKey;
import com.practice.domain.Member;
import com.practice.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * Cacheable의 동작 방식은 캐시에서 메서드의 파라미터로 캐시를 먼저 조회합니다.
     * 제가 사용한 Redis에 데이터가 있을 경우는 Redis에 저장된 데이터를 그대로 반환해주고, 없을 경우는 DB에 직접 조회합니다.
     * Cacheable을 통해 저장된 데이터는 어노테이션에 설정된 value::key 의 형태로 Redis의 Key로 저장됩니다.
     * 그에 대한 값은 CustomUserDetails의 형태로 저장됩니다.
     */
    //TODO - 토큰을 가지고 요청할 때 마다 DB에서 회원을 조회하는 것을 줄이기 위해 Cacheable 어노테이션을 이용 가능하다.
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CacheKey.USER, key = "#username", unless = "#result == null")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsernameWithAuthority(username).orElseThrow(() -> new NoSuchElementException("없는 회원입니다."));
        return CustomUserDetails.of(member);
    }
}
