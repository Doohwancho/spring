package org.apache.coyote.http11.handler;

import nextstep.jwp.controller.HomeController;
import nextstep.jwp.controller.UserController;
import nextstep.jwp.exception.UncheckedServletException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestHandlerMapping {

    private static final RequestHandlerMapping instance = new RequestHandlerMapping();
    private static final Map<String, RequestHandler> handlers = new ConcurrentHashMap<>(); //쓰레드 충돌하면 안되니까 Concurrent로 선언

    static {
        handlers.put("/", HomeController.getInstance());
        handlers.put("/register", UserController.getInstance());
//        handlers.put("/login", LoginController.getInstance());
    }

    private RequestHandlerMapping() {}

    public static RequestHandlerMapping getInstance() {
        return instance;
    }

    public RequestHandler getHandler(final String path) {
        validateHandlerExistence(path);
        return handlers.get(path);
    }

    private void validateHandlerExistence(final String path) {
        if (!handlers.containsKey(path)) {
            throw new UncheckedServletException("Invalid Uri");
        }
    }
}
