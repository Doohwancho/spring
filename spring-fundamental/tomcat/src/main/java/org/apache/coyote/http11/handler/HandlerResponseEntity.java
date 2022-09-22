package org.apache.coyote.http11.handler;

import org.apache.coyote.http11.response.HttpResponseHeader;
import org.apache.coyote.http11.util.HttpStatus;

import java.util.HashMap;

public class HandlerResponseEntity {

    private static final String EMPTY_BODY = "";

    private final HttpStatus httpStatus;
    private final HttpResponseHeader httpResponseHeader;
    private final String body;
    private final String resource;

    public HandlerResponseEntity(final HttpStatus httpStatus, final HttpResponseHeader headers, final String body,
                                 final String resource) {
        this.httpStatus = httpStatus;
        this.httpResponseHeader = headers;
        this.body = body;
        this.resource = resource;
    }

    public static HandlerResponseEntity createWithResource(final String resource) {
        return createWithResource(new HttpResponseHeader(new HashMap<>()), resource); //body는 안넣네? header가 비었네?
    }

    public static HandlerResponseEntity createWithResource(final HttpResponseHeader headers, final String resource) {
        return new HandlerResponseEntity(HttpStatus.OK, headers, EMPTY_BODY, resource); //안넣는게 아니라 resource로 넣네?
    }

    public static HandlerResponseEntity createRedirectResponse(final HttpStatus httpStatus,
                                                               final HttpResponseHeader headers, final String path) {
        headers.addHeader("Location", path);
        return createResponseBody(httpStatus, headers, EMPTY_BODY);
    }

    public static HandlerResponseEntity createResponseBody(final HttpStatus httpStatus,
                                                           final HttpResponseHeader headers, final String body) {
        return new HandlerResponseEntity(httpStatus, headers, body, EMPTY_BODY);
    }

    public boolean isEmptyResource() {
        return resource.isEmpty();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public HttpResponseHeader getHttpHeader() {
        return httpResponseHeader;
    }

    public String getBody() {
        return body;
    }

    public String getResource() {
        return resource;
    }
}
