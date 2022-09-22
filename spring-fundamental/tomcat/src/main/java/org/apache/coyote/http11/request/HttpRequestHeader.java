package org.apache.coyote.http11.request;

import jakarta.servlet.http.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestHeader {

    private static final Logger log = LoggerFactory.getLogger(HttpRequestHeader.class);

    private static final int NAME_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String FORM_DATA_CONTENT_TYPE = "application/x-www-form-urlencoded";

    private final Map<String, Object> headers;

    private HttpRequestHeader(final Map<String, Object> headers) {
        this.headers = headers;
    }

    public static HttpRequestHeader from(final List<String> rawHeader) { //undo static! its for logging temporarily!
        final Map<String, Object> parsedHeaders = new HashMap<>();

        for (final String header : rawHeader) {
            parseRawHeader(parsedHeaders, header);
        }

//        if (parsedHeaders.containsKey("Cookie")) {
//            log.info(parsedHeaders.containsKey("Cookie")+""); //true
//            log.info(parsedHeaders.get("Cookie").toString()); //Idea-e3e12a8e=7fa16e91-e91d-4168-8bfb-f747ad3be1f4
//            parsedHeaders.put("Cookie", Cookie.from(parsedHeaders.get("Cookie").toString()));
//        }

        return new HttpRequestHeader(parsedHeaders);
    }

    private static void parseRawHeader(final Map<String, Object> parsedHeaders, final String header) {
        final String[] parsedHeader = header.split(": ");
        final String headerName = parsedHeader[NAME_INDEX];
        final String headerValue = parsedHeader[VALUE_INDEX].trim();
        parsedHeaders.put(headerName, headerValue);
    }

    public boolean isFormDataType() {
        return headers.containsKey("Content-Type") && headers.get("Content-Type").equals(FORM_DATA_CONTENT_TYPE);
    }

    public boolean contains(final String headerName) {
        return headers.containsKey(headerName);
    }

    public String getHeader(final String headerName) {
        return (String) headers.get(headerName);
    }

//    public Cookie getCookies() {
//        if (headers.containsKey("Cookie")) {
//            return (Cookie) headers.get("Cookie");
//        }
//        return Cookie.createEmptyCookie();
//    }

}

