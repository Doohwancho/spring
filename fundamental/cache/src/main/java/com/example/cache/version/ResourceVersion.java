package com.example.cache.version;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//version의 VO
@Component
public class ResourceVersion {

    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyyMMddHHmmSS"; //초단위까지 입력되서 unique하게 만들려는 거구나

    private String version;

    @PostConstruct
    public void init() {
        this.version = now();
    }

    public String getVersion() {
        return version;
    }

    private static String now() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
        return LocalDateTime.now().format(formatter);
    }
}