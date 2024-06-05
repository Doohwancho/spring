package com.example.appkata.module.account.domain;


import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;

    public Account(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void assignId(Long nextId) {
        this.id = nextId;
    }

    public void updateUsername(String username) {
        this.username = username;
    }
}
