package com.cho.rest;

import com.cho.rest.robot.Robot;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RobotControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

//    @Test
//    public void createRobot_Success_Test() throws Exception {
//        Robot robot = Robot.builder()
//                .id(1L)
//                .age(132)
//                .name("RobotV")
//                .build();
//
//        mockMvc.perform(post("/api/robots")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .accept(MediaTypes.HAL_JSON)
//                        .content(objectMapper.writeValueAsString(robot))
//                        )
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("id").exists())
//                .andExpect(jsonPath("age").exists())
//                .andExpect(jsonPath("name").exists());
//    }
}