package com.tdd.tddTest.api.restTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.tddTest.domain.car.Car;
import com.tdd.tddTest.dto.car.CarDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("가장 기초적인 http request 형태")
    public void restTemplateBasicTest() throws JsonProcessingException {
        //http header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        //parameter binding(map || dto object -> json)
//        Map<String, Object> map = new HashMap<>();
//        map.put("msg", "Send Request");
//        String param1 = objectMapper.writeValueAsString(map);
        String param2 = objectMapper.writeValueAsString(CarDto.builder().id(1).carName("lambo").doors(4).build());

        //HttpEntity에 헤더 및 params 설정
        HttpEntity entity = new HttpEntity(param2, httpHeaders);

        //restTemplate.exchange()를 통해 url로 http request
        ResponseEntity<String> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/v1/cars", HttpMethod.POST, entity, String.class);

        //요청 후 응답 확인
        assertTrue(responseEntity.getStatusCode().value() == 200);
        assertNotNull(responseEntity.getBody());

        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
    }

    @Test
    @DisplayName("http GET request, get single car by id")
    public void testGetCarById() {
        Car car = restTemplate.getForObject(restTemplate.getRootUri() + "/api/v1/cars/1", Car.class);
        System.out.println(car.getCarName());
        assertNotNull(car);
    }

    @Test
    @DisplayName("http GET request, get all cars")
    public void testGetAllCars() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(restTemplate.getRootUri() + "/api/v1/cars",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("http POST request")
    public void testCreateCar() {
        Car car = new Car();
        car.setCarName("Prius");
        car.setDoors(4);

        ResponseEntity<Car> postResponse = restTemplate.postForEntity(restTemplate.getRootUri() + "/api/v1/cars", car, Car.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    @DisplayName("http PUT request")
    public void testUpdateCar() {
        int id = 1;
        Car car = restTemplate.getForObject(restTemplate.getRootUri() + "/api/v1/cars/" + id, Car.class);
        car.setCarName("Tesla");
        car.setDoors(2);

        restTemplate.put(restTemplate.getRootUri() + "/api/v1/cars/" + id, car);

        Car updatedCar = restTemplate.getForObject(restTemplate.getRootUri() + "/api/v1/cars/" + id, Car.class);
        assertNotNull(updatedCar);
    }
}

/*
---
restTemplate method

getForObject	GET	    HTTP GET 요청 후 결과는 객체로 반환
getForEntity	GET	    HTTP GET 요청 후 결과는 ResponseEntity로 반환
postForLocation	POST	HTTP POST 요청 후 결과는 헤더에 저장된 URL을 반환
postForObject	POST	HTTP POST 요청 후 결과는 객체로 반환
postForEntity	POST	HTTP POST 요청 후 결과는 ResponseEntity로 반환
delete	DELETE	HTTP    DELETE 요청
headForHeaders	HEADER	HTTP HEAD 요청 후 헤더정보를 반환
put	            PUT	    HTTP PUT 요청
patchForObject	PATCH	HTTP PATCH 요청 후 결과는 객체로 반환
optionsForAllow	OPTIONS	지원하는 HTTP 메소드를 조회
exchange	    Any	    원하는 HTTP 메소드 요청 후 결과는 ResponseEntity로 반환
execute	        Any	    Request/Response의 콜백을 수정


 */