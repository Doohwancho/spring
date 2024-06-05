package com.example.nextstep.webserver;

import com.example.nextstep.webserver.requestHandler.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private static final Logger log = LoggerFactory.getLogger(WebServer.class);
    private static final int DEFAULT_PORT = 8080;

    public static void main(String args[]) throws Exception {
        //포트 설정하는 곳. 서버 기본 포트 8080
        int port = 0;
        if (args == null || args.length == 0) {
            port = DEFAULT_PORT;
        } else {
            port = Integer.parseInt(args[0]);
        }


        //클라이언트의 요청을 받을 8080포트에 서버소켓 생성
        try (ServerSocket listenSocket = new ServerSocket(port)) {
            log.info("Web Application Server started {} port.", port);

            // 클라이언트가 연결될때까지 대기한다.
            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
                RequestHandler requestHandler = new RequestHandler(connection); //client에서 온 connection에게 RequestHandler를 붙여준다.
                requestHandler.start();
            }
        }
    }
}