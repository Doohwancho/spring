package com.example.productorderservice.playing;

import java.util.HashMap;
import java.util.Map;

class FunRepository {
    private Map<Long, Fun> persistence = new HashMap<>();
    private Long sequence = 0L;

    public void save(Fun fun) {
        fun.assignId(++sequence);
        persistence.put(fun.getId(), fun);
    }
}
