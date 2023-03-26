package org.example.com.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JWTTokenTest {

    static void printClaim(String key, Claim value){
        if(value.isNull()){
            System.out.printf("%s:%s\n", key, "none");
            return;
        }
        if(value.asString() != null){
            System.out.printf("%s:{str}%s\n", key, value.asString());
            return;
        }
        if(value.asLong() != null){
            System.out.printf("%s:{lng}%d\n", key, value.asLong());
            return;
        }
        if(value.asInt() != null ){
            System.out.printf("%s:{int}%d\n", key, value.asInt());
            return;
        }
        if(value.asBoolean() != null){
            System.out.printf("%s:{bol}%b\n", key, value.asBoolean());
            return;
        }
        if(value.asDate() != null){
            System.out.printf("%s:{dte}%s\n", key, value.asDate().toString());
            return;
        }
        if(value.asDouble() != null){
            System.out.printf("%s:{dbl}%f\n", key, value.asDouble());
            return;
        }
        String[] values = value.asArray(String.class);
        if(values != null){
            System.out.printf("%s:{arr}%s\n", key, Stream.of(values).collect(Collectors.joining(",")));
            return;
        }
        Map valueMap = value.asMap();
        if(valueMap != null) {
            System.out.printf("%s:{map}%s\n", key, valueMap);
            return;
        }
        System.out.println("====>> unknown type for :"+key);
    }


    //TODO - j-c-1. JWT 토큰이 잘 만들어 진다.
    @DisplayName("1. JWT 토큰이 잘 만들어 진다.")
    @Test
    @Disabled
    void test_() throws InterruptedException {

        //jwt = header + payload + verify signature(base64UrlEncode(header) + "." + base64UrlEncode(payload) + "." + secret password)
        //header = "alg":"HS256", "typ":"JWT"
        //payload = "sub":"jongwon", "exp":1620000000, "role":["ROLE_ADMIN", "ROLE_USER"]


        Algorithm AL = Algorithm.HMAC256("hello"); //보통 무난하게 HMAC256로 암호화 하고, 저 "hello" secret password가 털리면 안돼!!
        String token = JWT.create()
                .withSubject("jongwon") //payload에 들어가는 사용자
                .withClaim("exp", Instant.now().getEpochSecond()+2) //payload에 정해진 필드가 아니라, 사용자가 직접 적어놓는 것을 Claim이라고 부른다. 2초 후에 만료.
                .withArrayClaim("role", new String[]{"ROLE_ADMIN", "ROLE_USER"})
                .sign(AL);
        System.out.println(token);

        Thread.sleep(1000);

        DecodedJWT decode = JWT.decode(token); //그냥 decode해도 algorithm으로 decode한 것과 똑같이 값이 다 나온다.
        /*
            eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb25nd29uIiwicm9sZSI6WyJST0xFX0FETUlOIiwiUk9MRV9VU0VSIl0sImV4cCI6MTY3OTY2MDI5MH0.U7B6lD9i_ijdn7a82Hg4k8Q6cBu1eQlg4i8O9lfXA-4
            typ:{str}JWT
            alg:{str}HS256
            =======
            sub:{str}jongwon
            role:{arr}ROLE_ADMIN,ROLE_USER
            exp:{lng}1679660290
         */
//        DecodedJWT decode = JWT.require(AL).build().verify(token);


        printClaim("typ", decode.getHeaderClaim("typ"));
        printClaim("alg", decode.getHeaderClaim("alg"));
        System.out.println("=======");
        decode.getClaims().forEach(JWTTokenTest::printClaim);

        Thread.sleep(2000);

        assertThrows(TokenExpiredException.class, ()->{ //토큰이 만료하면, TokenExpiredException이 발생한다.
            JWT.require(AL).build().verify(token);
        });
    }

}
