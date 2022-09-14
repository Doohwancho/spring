package com.example.di.nextstep;

public class User {

    private long id; //removed final for setter
    private String account; //removed final for setter

    public User(long id, String account) {
        this.id = id;
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setAccount(String account){
        this.account = account;
    }
}
