package com.cho.rest.myTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class BasicRestAssuredTest {

    @Test
    @DisplayName("1. 기초 틀")
    public void BasicFramework(){
        RestAssured.
                given().
//                    auth().
//                    oauth2("myAuthenticationToken").
                when().
                    header("Content-Type","application/json").
//                    param(null).
//                    body(null).
                    get("https://reqres.in/api/users"). //or post(), update(), delete()
                then().
                    assertThat().
                    statusCode(200).
                    log().all();
    }


    @Test
    @DisplayName("2. json을 body에 첨부하기")
    public void IncludeJsonInBody(){
        JSONObject req = new JSONObject();
        req.put("name", "morpheus");
        req.put("job", "leader");

        RestAssured.
                given().
                when().
                    header("Content-Type","application/json").
                    accept(ContentType.JSON).
                    body(req.toJSONString()).
//                    param(null).
                    post("https://reqres.in/api/users"). //or post(), update(), delete()
                then().
                    assertThat().
                    statusCode(201).
                    log().all();
    }

    @Test
    @DisplayName("3. body에서 결과값 확인")
    public void CheckBodyEq(){
        RestAssured.
                given().
                when().
                header("Content-Type","application/json").
//                    param(null).
//                    body(null).
        get("https://reqres.in/api/users"). //or post(), update(), delete()
                then().
                assertThat().
                body("page", equalTo(1)).
                statusCode(200).
                log().all();
    }

    @Test
    @DisplayName("4. path parameter")
    public void usePathParam(){
        RestAssured.
                given().
//                    auth().
//                    oauth2("myAuthenticationToken").
        pathParam("pageNum", 2).
                when().
                header("Content-Type","application/json").
                //                    param(null).
                //                    body(null).
                        get("https://reqres.in/api/users/?page={pageNum}"). //or post(), update(), delete()
                then().
                assertThat().
                body("page", equalTo(2)).
                statusCode(200).
                log().all();
    }


    @Test
    @DisplayName("6. query parameter")
    public void useQueryParam(){
        RestAssured.
            given().
//                    auth().
//                    oauth2("myAuthenticationToken").
                queryParam("page", 2).
            when().
                header("Content-Type","application/json").
                //                    param(null).
                //                    body(null).
                get("https://reqres.in/api/users/"). //or post(), update(), delete()
            then().
                assertThat().
                body("page", equalTo(2)).
                statusCode(200).
                log().all();
    }

    private RequestSpecification requestSpec;

    @Before
    public void requestSpec으로_재활용_setup() {
        requestSpec =
                new RequestSpecBuilder().
                        setBaseUri("https://reqres.in"). //setPort(12004). //port도 셋업가능. 로컬 테스트시 톰캣쓰면 8080박고 시작.
                        build();
    }

    @Test
    @DisplayName("7. RequestSpecBuilder 우려먹기")
    public void useRequestSpec() {
        RestAssured.
                given().
                    spec(requestSpec).
                when().
                    get("/api/users/").
                then().
                    assertThat().
                        body("page", equalTo(1)).
                    statusCode(200).
                    log().all();
    }




//    @Test
//    @DisplayName("8. auth token 재활용하기")
//    public void Auth토큰_재활용하는법() {
//
//        String token =
//                given().
//                        spec(requestSpec).
//                        auth().
//                        preemptive().
//                        basic("john", "demo").
//                        when().
//                        get("/token").
//                        then().
//                        extract().
//                        path("token"); //먼저 받고,
//
//        given().
//                spec(requestSpec).
//                auth().
//                oauth2(token). //찐 테스트시 첨부
//                when().
//                get("/secure/customer/12212").
//                then().
//                assertThat().
//                statusCode(200);
//    }

//    @Test
//    @DisplayName("9. 리턴값을 자바 객체로 받기")
//    public void 리턴값을_자바객체로_받기() {
//
//        AccountResponse accountResponse =
//            RestAssured.
//                given().
//                    spec(requestSpec).
//                when().
//                    get("/customer/12212/accounts").
//                    as(AccountResponse.class);
//
//        assertEquals(3, accountResponse.getAccounts().size());
//    }
}
