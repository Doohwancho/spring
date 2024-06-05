package com.example.nextstep.controller;

import com.example.nextstep.webserver.http.HttpRequest;
import com.example.nextstep.webserver.http.HttpResponse;
import com.example.nextstep.webserver.http.HttpMethod;

public abstract class AbstractController implements Controller{

    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
        HttpMethod method = httpRequest.getMethod();
        if (method.isPost()) {
            doPost(httpRequest, httpResponse);
        }

        if (method.isGet()) {
            doGet(httpRequest, httpResponse);
        }
    }

    protected void doPost(HttpRequest httpRequest, HttpResponse httpResponse){};
    protected void doGet(HttpRequest httpRequest, HttpResponse httpResponse){};

}
