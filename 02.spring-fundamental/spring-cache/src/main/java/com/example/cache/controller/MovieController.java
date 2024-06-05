package com.example.cache.controller;

import com.example.cache.domain.Movie;
import com.example.cache.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("api/movie")
    public void save() {
        for(int i = 0; i < 5; i++){
            Movie movie = new Movie("올드보이 "+i);
            movieService.save(movie);
        }
    }
}

