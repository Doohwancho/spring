package com.example.cache.service;

import com.example.cache.domain.Movie;
import com.example.cache.dto.MovieRequestDto;
import com.example.cache.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    //DB에 데이터 저장
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    //데이터 조회함과 동시에 캐시에 저장
    @Cacheable(value = "movie")
    public Movie get(Long id) {
        return movieRepository.findById(id).orElseThrow(
                () -> new NullPointerException("no book"));
    }

    @CachePut(value="movie", key = "#id") //캐시 내용 수정
    @Transactional
    public void update(Long id, MovieRequestDto movieRequestDto) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new NullPointerException("없는 영화")
        );
        movie.update(movieRequestDto);
    }


    @CacheEvict(value = "movie") //캐시 삭제
    public void clearMovieCache() {
        //scheduler를 이용해 일정한 주기마다 캐시 삭제시켜주거나,
        //aop이용해서 특정 함수가 실행됬을 때 캐시 삭제시켜줄 수 있다.
    }
}
