package com.cos.person.repository;

import java.util.ArrayList;
import java.util.List;

import com.cos.person.domain.User;
import com.cos.person.dto.JoinReqDto;
import com.cos.person.dto.UpdateReqDto;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        users.add(new User(1, "ssar", "1234", "0102222"));
        users.add(new User(2, "cos", "1234", "0102222"));
        users.add(new User(3, "love", "1234", "0102222"));
        return users;
    }

    public User findById(int id){
        return new User(1, "ssar", "1234", "0102222");
    }

    public void save(JoinReqDto dto) {
        System.out.println("DB에 insert하기");
    }

    public void delete(int id) {
        System.out.println("DB에 삭제하기");
    }

    public void update(int id, UpdateReqDto dto) {
        // DAO연결해서 실행하다가 익섹션 터짐
        throw new IllegalArgumentException("아규먼트를 잘못 넣음");
        //System.out.println("DB에 수정하기");
    }
}

