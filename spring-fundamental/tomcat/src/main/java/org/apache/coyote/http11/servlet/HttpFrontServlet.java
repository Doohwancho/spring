package org.apache.coyote.http11.servlet;

import nextstep.jwp.controller.ControllerAdvice;
import org.apache.coyote.http11.handler.HandlerResponseEntity;
import org.apache.coyote.http11.handler.RequestHandler;
import org.apache.coyote.http11.handler.RequestHandlerMapping;
import org.apache.coyote.http11.request.HttpRequest;
import org.apache.coyote.http11.response.ResponseEntity;
import org.apache.coyote.http11.response.file.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;




public class HttpFrontServlet {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    private static final HttpFrontServlet instance = new HttpFrontServlet(
            RequestHandlerMapping.getInstance(), ControllerAdvice.getInstance());

    private final RequestHandlerMapping requestHandlerMapping;
    private final ControllerAdvice controllerAdvice;

    public HttpFrontServlet(final RequestHandlerMapping requestHandlerMapping, final ControllerAdvice controllerAdvice) {
        this.requestHandlerMapping = requestHandlerMapping;
        this.controllerAdvice = controllerAdvice;
    }

    public static HttpFrontServlet getInstance() {
        return instance;
    }

    public ResponseEntity service(final HttpRequest httpRequest) {
        try {
            HandlerResponseEntity response = handleRequest(httpRequest);
//            final HandlerResponseEntity responseWithCookie = handleCookie(response);
            return createResponseEntity(response);
        } catch (final Exception exception) {
            return controllerAdvice.handleException(exception.getClass());
        }
    }

    private HandlerResponseEntity handleRequest(final HttpRequest httpRequest) {
        final RequestHandler handler = requestHandlerMapping.getHandler(httpRequest.getPath());

        HandlerResponseEntity test = handler.handle(httpRequest);

//        log.info("http status: "+ test.getHttpStatus().getStatusCode()+""); //http status: 200  when "/"
//        log.info("http header: "+ test.getHttpHeader().toString()); //http header:  when "/"
//        log.info("http response body: "+ test.getBody()); //http response body:  when  "/" -> why not "Hello World!?"
//        log.info("http resource: "+ test.getResource().toString()); //http resource: Hello world!

        return test;
    }

//    private HandlerResponseEntity handleCookie(HttpRequest httpRequest, HandlerResponseEntity response){
//        //I want Cookie
//        response.getHttpHeader().addCookie(httpRequest.getCookies().getCookie(), final String value);
//
//        return response;
//    }

    private ResponseEntity createResponseEntity(final HandlerResponseEntity response)
            throws IOException {
        if (response.isEmptyResource()) {
            return ResponseEntity.from(response);
        }

        final String resourcePath = response.getResource(); //아 resourcePath보고 resources/index.html에서 가져오는 거였구나!
        if (FileHandler.isStaticFilePath(resourcePath)) {
            return FileHandler.createFileResponse(resourcePath);
        }
        return ResponseEntity.createTextHtmlResponse(response);
    }
}