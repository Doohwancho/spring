package org.apache.coyote.http11.handler;

import org.apache.coyote.http11.request.HttpRequest;

public interface RequestHandler {

    HandlerResponseEntity handle(final HttpRequest httpRequest);
}

