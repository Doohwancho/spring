package nextstep.jwp.controller;


import org.apache.coyote.http11.handler.HandlerResponseEntity;
import org.apache.coyote.http11.handler.HttpRequestHandler;
import org.apache.coyote.http11.request.HttpRequest;
import org.apache.coyote.http11.response.HttpResponseHeader;
import org.apache.coyote.http11.util.HttpStatus;
import org.apache.coyote.http11.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeController extends HttpRequestHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final HomeController instance = new HomeController();

    private HomeController() {}

    public static HomeController getInstance() {
        return instance;
    }

    @Override
    public HandlerResponseEntity doGet(final HttpRequest httpRequest, final HttpResponseHeader responseHeader) {
        String resource = IOUtils.readResourceFile("/index");
        return HandlerResponseEntity.createWithResource(resource); //? -> 이걸 어떻게 /index.html로 바꿀 수 있지?
    }

    @Override
    public HandlerResponseEntity doPost(final HttpRequest httpRequest, final HttpResponseHeader responseHeader) {
        throw new IllegalStateException("Invalid Uri");
    }
}