package com.example.nextstep.controller;

import com.example.nextstep.db.Database;
import com.example.nextstep.model.User;
import com.example.nextstep.webserver.http.HttpRequest;
import com.example.nextstep.webserver.http.HttpResponse;

public class CreateUserController extends AbstractController {
    @Override
    public void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        // User DB 저장
        Database.addUser(new User(httpRequest.getParameter("userId"), httpRequest.getParameter("password"),
                httpRequest.getParameter("name"), httpRequest.getParameter("email")));

        // 302 response (location : index.html), 302 means "redirected"
        httpResponse.sendRedirect("/index.html");
    }

    //Deprecated
    @Override
    public void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        // User DB 저장
        Database.addUser(
                new User(httpRequest.getParameter("userId"), httpRequest.getParameter("password"),
                        httpRequest.getParameter("name"),
                        httpRequest.getParameter("email")));

        // 302 response (location : index.html), 302 means "redirected"
        httpResponse.sendRedirect("/index.html");

        return;
    }
}
