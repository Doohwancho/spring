package com.example.nextstep.webserver.requestHandler;

import com.example.nextstep.controller.Controller;
import com.example.nextstep.controller.CreateUserController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static final Map<String, Controller> controllers;

    static {
        controllers = new HashMap<>();
        controllers.put("/user/create", new CreateUserController());
//        controllers.put("/user/list", new ListUserController());
//        controllers.put("/user/login", new LoginController());
    }

    public static Controller getController(String requestUrl) {
        return controllers.get(requestUrl);
    }
}
