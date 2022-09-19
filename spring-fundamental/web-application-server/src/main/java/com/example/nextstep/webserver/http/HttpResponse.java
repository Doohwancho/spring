package com.example.nextstep.webserver.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpResponse {
    private static final Logger log = LoggerFactory.getLogger(HttpResponse.class);
    private static final String WEB_APP_PATH = "./web-application-server/src/main/resources/";

    private DataOutputStream dos;
    private Map<String, String> header;

    public HttpResponse(OutputStream out) {
        dos = new DataOutputStream(out);
        header = new HashMap<>();
    }

    public void forward(HttpRequest request, String url) {
        try {
            String contentType = request == null ? "*/*" : makeContentType(request);
            log.info("contentType: "+contentType); //18:47:22.020 [Thread-0] INFO com.example.nextstep.webserver.http.HttpResponse - contentType: text/html

            byte[] body = new byte[0];
            body = Files.readAllBytes(new File(WEB_APP_PATH + url).toPath());

            //simple index.html to byte[]
            log.info(Arrays.toString(body)); //18:47:22.021 [Thread-0] INFO com.example.nextstep.webserver.http.HttpResponse - [60, 33, 68, 79, 67, 84, 89, 80, 69, 32, 104, 116, 109, 108, 62, 10, 60, 104, 116, 109, 108, 32, 108, 97, 110, 103, 61, 34, 101, 110, 34, 62, 10, 60, 104, 101, 97, 100, 62, 10, 32, 32, 32, 32, 60, 109, 101, 116, 97, 32, 99, 104, 97, 114, 115, 101, 116, 61, 34, 85, 84, 70, 45, 56, 34, 62, 10, 32, 32, 32, 32, 60, 116, 105, 116, 108, 101, 62, 84, 105, 116, 108, 101, 60, 47, 116, 105, 116, 108, 101, 62, 10, 60, 47, 104, 101, 97, 100, 62, 10, 60, 98, 111, 100, 121, 62, 10, 32, 32, 105, 110, 100, 101, 120, 46, 104, 116, 109, 108, 33, 10, 60, 47, 98, 111, 100, 121, 62, 10, 60, 47, 104, 116, 109, 108, 62]

            response200Header(body.length, contentType);
            responseBody(body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private String makeContentType(HttpRequest request) {
        String[] accepts = request.getHeader("Accept").split(",");
        String contentType = "*/*";
        String fileExt = request.getPath().substring(request.getPath().lastIndexOf(".") + 1);

        for (String accept : accepts) {
            if (accept.contains(fileExt)) {
                contentType = accept;
                break;
            }
        }
        return contentType;
    }

    private void response200Header(int lengthOfBodyContent, String contentType) {
        addHeader("Content-Type", contentType);
        addHeader("Content-Length", Integer.toString(lengthOfBodyContent));

        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            processHeaders();
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush(); //버퍼를 비우면서, 버퍼안의 내용을 전송한다.
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void addHeader(String key, String value) {
        header.put(key, value);
    }

    private void processHeaders() {
        try {
            Set<String> keys = header.keySet();
            for (String key : keys) {
                dos.writeBytes(key + ": " + header.get(key) + "\r\n");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
