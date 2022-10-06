package com.example.cache.app;

import com.example.cache.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final MovieService movieService;

    @Override
    public void run(String... args) throws Exception {
        log.info("영화 검색하기");
        log.info("movieId=1 ------- " + movieService.get(1L));
        log.info("movieId=1 ------- " + movieService.get(1L));
        log.info("movieId=1 ------- " + movieService.get(1L));
    }
}

//2022-10-06 17:59:53.715  INFO 9649 --- [           main] com.example.cache.app.AppRunner          : movieId=1 ------- Movie(id=1, title=올드보이)
//2022-10-06 17:59:53.715  INFO 9649 --- [           main] com.example.cache.app.AppRunner          : movieId=1 ------- Movie(id=1, title=올드보이)
//2022-10-06 17:59:53.715  INFO 9649 --- [           main] com.example.cache.app.AppRunner          : movieId=1 ------- Movie(id=1, title=올드보이)
