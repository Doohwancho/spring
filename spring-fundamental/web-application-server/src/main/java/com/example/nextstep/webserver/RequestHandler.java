package com.example.nextstep.webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RequestHandler extends Thread { //client 한명 connection 당, 하나의 Request Handler, thread 할당
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            DataOutputStream dos = new DataOutputStream(out);

            byte[] body = "Hello World".getBytes(); //hello world를 body에 담아서 client로 response 보낸다.
            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    //Q. 다른 http header는 뭐가있을까?
    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush(); //버퍼 비워준다
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}

/*

---
console.log

http://localhost:8080 접속해보면,

화면에 Hello World라고 뜨면서,

16:28:20.116 [Thread-0] DEBUG com.example.nextstep.webserver.RequestHandler - New Client Connect! Connected IP : /0:0:0:0:0:0:0:1, Port : 57248
16:28:20.897 [Thread-1] DEBUG com.example.nextstep.webserver.RequestHandler - New Client Connect! Connected IP : /0:0:0:0:0:0:0:1, Port : 57249
16:28:20.897 [Thread-2] DEBUG com.example.nextstep.webserver.RequestHandler - New Client Connect! Connected IP : /0:0:0:0:0:0:0:1, Port : 57250

요렇게 뜬다.


 */
