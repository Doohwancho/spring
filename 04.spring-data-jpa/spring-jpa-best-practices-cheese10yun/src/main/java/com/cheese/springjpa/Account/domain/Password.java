package com.cheese.springjpa.Account.domain;

import com.cheese.springjpa.Account.exception.PasswordFailedExceededException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * ---
 * 비밀번호 요구사항
 *
 *
 * 1. 비밀번호 만료 기본 14일 기간이 있다.
 * 2. 비밀번호 만료 기간이 지나는 것을 알 수 있어야 한다.
 * 3. 비밀번호 5회 이상 실패했을 경우 더 이상 시도를 못하게 해야 한다.
 * 4. 비밀번호가 일치하는 경우 실패 카운트를 초기화 해야한다
 * 5. 비밀번호 변경시 만료일이 현재시간 기준 14로 연장되어야한다.
 *
 *
 * ---
 * DDD 장점
 *
 *
 * 위의 요구 사항을 만족하는 로직들은 Password 객체 안에 있고 Password 객체를 통해서 모든 작업들이 이루어집니다.
 * 그래서 결과적으로 Password 관련 테스트 코드도 작성하기 쉬워지고, 이렇게 작은 단위로 테스트 코드를 작성하면 실패했을 때 원인도 찾기 쉬워집니다.
 */

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "비밀번호", description = "비밀번호")
public class Password {

    @Column(name = "password", nullable = false)
    @ApiModelProperty(value = "password", example = "1234", required = true)
    private String value;

    @Column(name = "password_expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "password_failed_count", nullable = false)
    private int failedCount;

    @Column(name = "password_ttl")
    private long ttl;

    @Builder
    public Password(final String value) {
        this.ttl = 1209_604; // 1209_604 is 14 days
        this.value = encodePassword(value);
        this.expirationDate = extendExpirationDate();
    }

    public boolean isMatched(final String rawPassword) {
        if (failedCount >= 5)
            throw new PasswordFailedExceededException();

        final boolean matches = isMatches(rawPassword);
        updateFailedCount(matches);
        return matches;
    }

    public void changePassword(final String newPassword, final String oldPassword) {
        if (isMatched(oldPassword)) {
            value = encodePassword(newPassword);
            extendExpirationDate();
        }
    }


    public boolean isExpiration() {
        return LocalDateTime.now().isAfter(expirationDate);
    }

    private LocalDateTime extendExpirationDate() {
        return LocalDateTime.now().plusSeconds(ttl);
    }

    private String encodePassword(final String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private void updateFailedCount(boolean matches) {
        if (matches)
            resetFailedCount();
        else
            increaseFailCount();
    }

    private void resetFailedCount() {
        this.failedCount = 0;
    }

    private void increaseFailCount() {
        this.failedCount++;
    }

    private boolean isMatches(String rawPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, this.value);
    }

}
