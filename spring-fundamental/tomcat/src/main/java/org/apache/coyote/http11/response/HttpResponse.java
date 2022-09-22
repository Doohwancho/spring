package org.apache.coyote.http11.response;

import org.apache.coyote.http11.util.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpResponse {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final HttpStatusLine statusLine;
    private final HttpResponseHeader httpResponseHeader;
    private final String responseBody;

    private HttpResponse(final HttpStatus httpStatus, final HttpResponseHeader httpResponseHeader, final String responseBody) {
        this.statusLine = new HttpStatusLine(httpStatus);
        this.httpResponseHeader = httpResponseHeader;
        this.responseBody = responseBody;
    }

    public static HttpResponse from(final ResponseEntity responseEntity) {
        return new HttpResponse(responseEntity.getHttpStatus(), responseEntity.getHttpHeader(), responseEntity.getBody());
    }

    public String createResponse() {
//        log.info("httpResponseHeader is null: "+ (httpResponseHeader == null));
//        log.info("httpResponseHeader's getCookie is null: "+ (httpResponseHeader.getCookies()));
//        log.info("httpResponseHeader in general: "+httpResponseHeader.toString());

        return String.join("\r\n",
                statusLine.toString(),
                "Content-Length: " + responseBody.getBytes().length + " ",
                httpResponseHeader.toString(),
                responseBody);
    }
}
