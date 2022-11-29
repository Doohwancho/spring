package me.hajoo.rollbackfor.dto;

import lombok.Getter;

@Getter
public class CreateUserVo {

    String email;
    String password;
    String name;
    String errorType;

}
