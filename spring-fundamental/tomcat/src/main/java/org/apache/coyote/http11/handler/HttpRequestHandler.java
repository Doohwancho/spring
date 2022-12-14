package org.apache.coyote.http11.handler;

import org.apache.coyote.http11.request.HttpRequest;
import org.apache.coyote.http11.response.HttpResponseHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public abstract class HttpRequestHandler implements RequestHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    protected String EMPTY_BODY = "";

    @Override
    public HandlerResponseEntity handle(final HttpRequest httpRequest) {
        final HttpResponseHeader responseHeader = new HttpResponseHeader(new HashMap<>());
        if (httpRequest.isGet()) {
            log.info("doGet() triggered!");
            return doGet(httpRequest, responseHeader);
        }
        return doPost(httpRequest, responseHeader);
    }

    protected abstract HandlerResponseEntity doGet(HttpRequest httpRequest, HttpResponseHeader responseHeader);

    protected abstract HandlerResponseEntity doPost(HttpRequest httpRequest, HttpResponseHeader responseHeader);
}

