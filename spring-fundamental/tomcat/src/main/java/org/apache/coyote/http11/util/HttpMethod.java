package org.apache.coyote.http11.util;

public enum HttpMethod {

    GET, POST;

    public static HttpMethod of(final String name) {
        return valueOf(name);
    }

    public boolean isGet() {
        return this == GET;
    }
}
