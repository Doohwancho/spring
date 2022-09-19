package com.example.nextstep.webserver.http;

import com.example.nextstep.webserver.requestHandler.RequestHandler;
import com.example.nextstep.webserver.util.HttpMethod;
import com.example.nextstep.webserver.util.RequestLine;
import com.example.nextstep.webserver.util.HttpRequestUtils;
import com.example.nextstep.webserver.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class HttpRequest {
    private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);

    private RequestLine requestLine;
    private Map<String, String> header;
    private Map<String, String> cookies;
    private Map<String, String> parameter;

    public HttpRequest(InputStream in) throws IOException {
        InputStreamReader reader = new InputStreamReader(in, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        // Parse StartLine
        String startLine = br.readLine();
        if (startLine == null || "".equals(startLine)) {
            return;
        }

        requestLine = new RequestLine(startLine);
        header = parseHeader(br);

        // Parse Request Message Body
        if (requestLine.getMethod().isPost()) {
            String body = IOUtils.readData(br, Integer.parseInt(header.getOrDefault("Content-Length", "0")));
            parameter = HttpRequestUtils.parseQueryString(body);
        } else {
            parameter = requestLine.getParams();
        }
        for( String key : parameter.keySet() ) {
            System.out.println(String.format("파라미터 키 : %s, 값 : %s", key, parameter.get(key))); //http://localhost:8080/?id=awesome -> 파라미터 키 : id, 값 : awesome
        }

        // Parse Cookies
        cookies = HttpRequestUtils.parseCookies(getHeader("Cookie"));
        for( String key : cookies.keySet() ) {
            System.out.println(String.format("쿠키 키 : %s, 값 : %s", key, cookies.get(key))); //키 : Idea-e3e12a8e, 값 : 7fa16e91-e91d-4168-8bfb-f747ad3be1f4
        }

    }

    private Map<String, String> parseHeader(BufferedReader br) throws IOException {
        Map<String,String> headers = new HashMap<>();
        while (true) {
            String s = br.readLine();
            if (s == null || "".equals(s)) {
                break;
            }

            HttpRequestUtils.Pair pair = HttpRequestUtils.parseHeader(s);
            headers.put(pair.getKey(), pair.getValue());

            log.info("HttpRequest - headers.key: "+pair.getKey()+" headers.value: "+pair.getValue());
        }
        return headers;
    }
    /**

    console.log

    http://localhost:8080/

    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: Host headers.value: localhost:8080
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: Connection headers.value: keep-alive
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: Cache-Control headers.value: max-age=0
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: sec-ch-ua headers.value: "Google Chrome";v="105", "Not)A;Brand";v="8", "Chromium";v="105"
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: sec-ch-ua-mobile headers.value: ?0
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: sec-ch-ua-platform headers.value: "macOS"
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: Upgrade-Insecure-Requests headers.value: 1
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: User-Agent headers.value: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: Accept headers.value: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng, /;q=0.8,application/signed-exchange;v=b3;q=0.9
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: Sec-Fetch-Site headers.value: none
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: Sec-Fetch-Mode headers.value: navigate
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: Sec-Fetch-User headers.value: ?1
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: Sec-Fetch-Dest headers.value: document
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: Accept-Encoding headers.value: gzip, deflate, br
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: Accept-Language headers.value: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
    18:17:25.207 [Thread-0] INFO com.example.nextstep.webserver.http.HttpRequest - HttpRequest - headers.key: Cookie headers.value: Idea-e3e12a8e=7fa16e91-e91d-4168-8bfb-f747ad3be1f4

    */

    public String getHeader(String key) {
        return header.getOrDefault(key, "");
    }
    public String getPath() {
        return requestLine.getPath();
    }
    public HttpMethod getMethod() {
        return requestLine.getMethod();
    }
    public String getParameter(String key) {
        return parameter.getOrDefault(key, "");
    }
    public boolean isStaticFileRequest() {
        return requestLine.getPath().contains(".");
    }
    public boolean isLogin() {
        return Boolean.parseBoolean(cookies.getOrDefault("logined", "false"));
    }
}
