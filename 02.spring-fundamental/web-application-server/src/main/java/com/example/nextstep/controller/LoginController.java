package com.example.nextstep.controller;

import com.example.nextstep.db.Database;
import com.example.nextstep.model.User;
import com.example.nextstep.webserver.http.HttpRequest;
import com.example.nextstep.webserver.http.HttpResponse;

public class LoginController extends AbstractController {
    @Override
    public void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        User user = Database.findUserById(httpRequest.getParameter("userId"));
        if (user != null && user.getPassword().equals(httpRequest.getParameter("password"))) {
            httpResponse.addHeader("Set-Cookie", "logined=true"); //로그인 성공했으니까,쿠키 줄게!.
            httpResponse.sendRedirect("/index.html");
            return;
        }

        httpResponse.sendRedirect("/user/login_failed.html");
    }

}