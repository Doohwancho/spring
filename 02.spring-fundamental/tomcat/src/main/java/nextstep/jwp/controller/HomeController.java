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

//        log.info("inside HomeController's doGet");
//        log.info(httpRequest.getHeaders().getCookies().toString());
//        return HandlerResponseEntity.createWithResource(httpRequest.getHeader(), resource); //여기에서 헤더를 넣는게 좋을까? 아니면 여긴 컨트롤러니까 다른 곳에서 넣는게 좋을까? 다른 곳에서 넣는게 좋겠지? SessionManager 저기에서 처리하면 좋을 것 같은데
//        return HandlerResponseEntity.createWithResource(new HttpResponseHeader(httpRequest.getHeaders(), httpRequest.getCookies()) , resource);
        return HandlerResponseEntity.createWithResource(resource);
    }

    @Override
    public HandlerResponseEntity doPost(final HttpRequest httpRequest, final HttpResponseHeader responseHeader) {
        throw new IllegalStateException("Invalid Uri");
    }
}
