package com.practice.fixture;

import com.practice.dto.JoinDto;
import com.practice.dto.LoginDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.UUID;

import static io.restassured.RestAssured.given;


public class MemberSteps {

    public static ExtractableResponse<Response> 일반유저생성() {
        String userName = UUID.randomUUID().toString();
        String userPassword = "password";
        String userIntro = "userIntro";

        return RestAssured.given().log().all()
                .body(JoinDto.builder().username(userName).password(userPassword).intro(userIntro).build())
                .contentType("application/json")
                .when().post("/member/signup")
                .then().log().all()
                .statusCode(200)
                .extract();
    }
    public static ExtractableResponse<Response> 일반유저생성(String username, String password) {
        String userName = username;
        String userPassword = password;
        String userIntro = "userIntro";

        return RestAssured.given().log().all()
                .body(JoinDto.builder().username(userName).password(userPassword).intro(userIntro).build())
                .contentType("application/json")
                .when().post("/member/signup")
                .then().log().all()
                .statusCode(200)
                .extract();
    }

    public static ExtractableResponse<Response> 로그인완료() {
        //given
        String userName = "username";
        String password = "password";
        일반유저생성(userName, password);

        //when
        return given()
                .log().all()
                .body(LoginDto.builder().username(userName).password(password).build())
                .contentType("application/json")
                .when()
                .post("/member/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract();
    }
}
