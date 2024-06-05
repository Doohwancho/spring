package com.practice.util;

import com.practice.domain.token.RefreshToken;
import com.practice.exception.message.ExceptionMessage;
import com.practice.exception.model.TokenCheckFailException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
@Data
public class JwtTokenUtil {
    // Token
    public static final Long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 1; // ACCESS 토근 만료 시간: 1시간 (1~2 hr is ideal)
    public static final Long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24 * 7; // Refresh 토큰 만료 시간 : 7일 (1~2 week is ideal)
    public static final Long REISSUE_EXPIRE_TIME = 1000L * 60 * 60 * 1; // Reissue 만료 시간 : 1시간 (same with access token 만료기간)

    // header
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer-";

    private static final String KEY_ROLES = "roles";

    @Value("${spring.jwt.secret}")
    private String secretKey; //TODO - application.yml에 jwt secret key를 적어놓는다.

    public String generateToken(String username, long expirationTime) {
        Claims claims = Jwts.claims().setSubject(username); //TODO - CLaims는 Map<String, Object> ("user-id", userId) 넣는 앤가 보네.
        claims.put("username", username);
        // claims.put(KEY_ROLES, roles);

        var now = new Date();
        var expireDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate) //TODO - 여기서 expire date 설정해주고,
                .signWith(SignatureAlgorithm.HS512, secretKey).compact(); //알고리즘 뭐쓸지 적어주네
    }

    public long getRemainTime(String token) {
        Date expiration = parseClaims(token).getExpiration();
        Date now = new Date();
        return expiration.getTime() - now.getTime();
    }

    public String parseToken(String token) {
        return parseClaims(token).getSubject();
    }

    public String resolveToken(String token) {
        if (!ObjectUtils.isEmpty(token) && token.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            return token.substring(JwtTokenUtil.TOKEN_PREFIX.length());
        }
        return null;
    }

    public Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new TokenCheckFailException(ExceptionMessage.TOKEN_VALID_TIME_EXPIRED);
        } catch (Exception e) {
            throw new TokenCheckFailException(ExceptionMessage.FAIL_TOKEN_CHECK);
        }
    }

    public void setRefreshTokenAtCookie(RefreshToken refreshToken) {
        Cookie cookie = new Cookie("RefreshToken", refreshToken.getRefreshToken());
//        cookie.setHttpOnly(true); //http only
//        cookie.setSecure(true); //https. 근데 웹사이트가 애초에 https를 허용하지 않아서, https:localhost:8080/main 하면 페이지가 안뜬다.
        cookie.setMaxAge(refreshToken.getExpiration().intValue()); //쿠키가 refresh token expiration time에 맞춰서 없어지네. 그건 6시간이고.
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        response.addCookie(cookie);
    }
}