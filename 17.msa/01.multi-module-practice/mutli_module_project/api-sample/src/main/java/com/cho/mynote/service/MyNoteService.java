package com.cho.mynote.service;

import com.cho.exception.MyNoteNotFoundException;
import com.cho.mynote.entity.MyNoteEntity;
import com.cho.mynote.repository.MyNoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyNoteService {
    
    private final MyNoteRepository myNoteRepository;
    
    public MyNoteService(MyNoteRepository myNoteRepository) {
        this.myNoteRepository = myNoteRepository;
    }
    
    public MyNoteEntity findById(Long id) {
        return myNoteRepository.findById(id).orElseThrow(() -> new MyNoteNotFoundException(id));
    }
    
}