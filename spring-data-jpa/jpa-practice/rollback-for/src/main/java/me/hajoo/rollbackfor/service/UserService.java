package me.hajoo.rollbackfor.service;

import lombok.RequiredArgsConstructor;
import me.hajoo.rollbackfor.domain.User;
import me.hajoo.rollbackfor.dto.CreateUserVo;
import me.hajoo.rollbackfor.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(CreateUserVo createUserVo) throws IOException {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(createUserVo, User.class);
        userRepository.save(user);
        if (createUserVo.getErrorType().equals("UNCHECKED"))
            throw new NullPointerException();
        else if (createUserVo.getErrorType().equals("CHECKED"))
            throw new IOException();
    }

    @Transactional(rollbackFor = Exception.class)
    public void signup_rollbackFor_exception(CreateUserVo createUserVo) throws IOException {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(createUserVo, User.class);
        userRepository.save(user);
        if (createUserVo.getErrorType().equals("UNCHECKED"))
            throw new NullPointerException();
        else if (createUserVo.getErrorType().equals("CHECKED"))
            throw new IOException();
    }

    public int find_users_length() {
        return userRepository.findAll().size();
    }

}
