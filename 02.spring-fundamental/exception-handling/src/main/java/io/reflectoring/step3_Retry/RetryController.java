package io.reflectoring.step3_Retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class RetryController {

    @Autowired
    private RestTemplate retryableRestTemplate;

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String employees() throws URISyntaxException {
        //이 url은 한번 접속하면, 에러남.
        //{"message":"Error Occured! Page Not found, contact rstapi2example@gmail.com"}

        //근데 재시도 하면, 정상적으로 데이터가 나옴.
        /*
        <!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 2.0//EN">
        <html><head>
        <title>301 Moved Permanently</title>
        </head><body>
        <h1>Moved Permanently</h1>
        <p>The document has moved <a href="https://dummy.restapiexample.com/api/v1/employeeszzz">here</a>.</p>
        </body></html>
         */

        final String baseUrl = "http://dummy.restapiexample.com/api/v1/employeeszzz";
        URI uri = new URI(baseUrl);
        ResponseEntity<String> exchange = retryableRestTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        return exchange.getBody();
    }
}
