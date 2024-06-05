package com.example.nextstep.controller;

import com.example.nextstep.webserver.http.HttpRequest;
import com.example.nextstep.webserver.http.HttpResponse;

public interface Controller {
    void service(HttpRequest httpRequest, HttpResponse httpResponse);
}

