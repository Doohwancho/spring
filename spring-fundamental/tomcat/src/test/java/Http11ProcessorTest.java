import org.apache.coyote.http11.Http11Processor;
import org.junit.jupiter.api.Test;
import support.StubSocket;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

class Http11ProcessorTest {

    @Test
    void process() {
        // given
        final var socket = new StubSocket();
        final var processor = new Http11Processor(socket);

        // when
        processor.process(socket);

        // then
        var expected = String.join("\r\n",
                "HTTP/1.1 200 OK ",
                "Content-Type: text/html;charset=utf-8 ",
                "Content-Length: 12 ",
                "",
                "Hello world!");

        assertThat(socket.output()).isEqualTo(expected);
    }

    @Test
    void index() throws IOException {
        // given
        final String httpRequest= String.join("\r\n",
                "GET /index.html HTTP/1.1 ",
                "Host: localhost:8080 ",
                "Connection: keep-alive ",
                "",
                "");

        final var socket = new StubSocket(httpRequest);
        final Http11Processor processor = new Http11Processor(socket);

        // when
        processor.process(socket);

        // then
        final URL resource = getClass().getClassLoader().getResource("static/index.html");
        var expected = "HTTP/1.1 200 OK \r\n" +
                "Content-Type: text/html;charset=utf-8 \r\n" +
                "Content-Length: 5564 \r\n" +
                "\r\n"+
                new String(Files.readAllBytes(new File(resource.getFile()).toPath()));

        assertThat(socket.output()).isEqualTo(expected);
    }
}

/*
---
index()

failed
expected ~/index.html
actual:

  "
 but was:
  "HTTP/1.1 302 Found
  Content-Length: 0
  Location: 404.html

  "

---
Q. 왜 테스트 index()에서 http status code 302떠서 실패하지?


prediction:
302은 redirect 되었다는 처리로 보이는데,
바로 index.html로 안쏘고 return redirected "/" 처리됬나?


answer:
-> 일단 302은 실패도 아니고, redirect도 아니다.
HttpStatus에서 보면, FOUND(302, "Found"), 라고 되어있다. 고로 실패 아님.

그리고 HomeController에서 보면,
private static final String HOME_BODY = "Hello world!";
return HandlerResponseEntity.createWithResource(HOME_BODY);

resources/index.html을 반환 안하고, "Hello world!" 반환하게 되어있다.
이걸 어떻게 resources/index.html을 반환하도록 만들지?


HttpResponseEntity에서,
1. HttpStatus
2. HttpHeader
3. body
4. resource

중에서, HomeController.java에서 넣는 "Hello world!"는 body가 아니라 resource로 넣어지네?

그리고 ResponseEntity에서 ResponseEntity 반환할 때, createTextHtmlResponse()에서 여태껏 resource였던애가 body가 된다?!



---
Q. 404.html이 출력되는데, 어디서 출력되는거지?
ControllerAdvice인가?



 */