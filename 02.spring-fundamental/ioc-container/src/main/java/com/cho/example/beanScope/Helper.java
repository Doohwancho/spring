package com.cho.example.beanScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Helper {
    @Autowired
    Single single;

    @Autowired
    Proto proto;

    public Single getSingle(){
        return single;
    }

    public Proto getProto(){
        return proto;
    }
}
