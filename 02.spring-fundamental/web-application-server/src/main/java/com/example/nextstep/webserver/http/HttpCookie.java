package com.example.nextstep.webserver.http;

import com.example.nextstep.webserver.util.HttpRequestUtils;

import java.util.Map;

public class HttpCookie {
    private Map<String, String> cookies;

    HttpCookie(String cookieValue) {
        cookies = HttpRequestUtils.parseCookies(cookieValue);
    }

    public String getCookies(String name) {
        return cookies.get(name);
    }
}
