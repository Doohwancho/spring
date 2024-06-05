package com.cho.example.environment.profile;

import org.springframework.context.annotation.Profile;

@org.springframework.stereotype.Service
@Profile("test") //"!test", &, | 도 사용 가능
public class Service {
}