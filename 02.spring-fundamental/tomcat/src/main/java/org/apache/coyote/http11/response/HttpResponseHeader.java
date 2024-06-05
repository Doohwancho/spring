package org.apache.coyote.http11.response;

import jakarta.servlet.http.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HttpResponseHeader {

    private static final Logger log = LoggerFactory.getLogger(HttpResponseHeader.class);

    private final Map<String, String> headers;
//    private Cookie cookies; //이게 null인게 만악의 근원

    public HttpResponseHeader(final Map<String, String> headers) {
        this.headers = headers;
    }

//    public HttpResponseHeader(final Map<String, String> headers, final Cookie cookies) {
//        this.headers = headers;
//        this.cookies = cookies;
//    }


    public void addHeader(final String headerName, final String value) {
        headers.putIfAbsent(headerName, value);
    }

//    public void addCookie(final String headerName, final String value) {
//        log.info("HttpResponseHeader addCookie(), headerName: "+headerName+" value: "+value);
//        cookies.addCookie(headerName, value);
//    } //JSESSIONID, be2c331e-400b-4627-86d1-d457b3f20e0e

    public String getHeader(final String headerName) {
        return headers.get(headerName);
    }

//    public Cookie getCookies() {
//        return cookies;
//    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

//        if(cookies != null){
//            stringBuilder.append(cookies.toString());
//        }

        for (final String name : headers.keySet()) {
            stringBuilder.append(name).append(": ").append(headers.get(name));
            appendCharsetToContentType(stringBuilder, name);
            stringBuilder.append(" \r\n");
        }

        return stringBuilder.toString();
    }

//    private Cookie parseHeaderToCookie() {
//        if (headers.containsKey("Cookie")) {
//            return new Cookie(headers.get("Cookie"));
//        }
//        return new Cookie();
//    }

    private void appendCharsetToContentType(final StringBuilder stringBuilder, final String name) {
        if (name.equals("Content-Type")) {
            stringBuilder.append(";charset=utf-8");
        }
    }
}

