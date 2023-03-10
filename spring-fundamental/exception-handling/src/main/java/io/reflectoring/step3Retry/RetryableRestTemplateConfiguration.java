package io.reflectoring.step3Retry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@EnableRetry
@Configuration
class RetryableRestTemplateConfiguration {

    //TODO - a-8. spring retry
    @Bean
    public RestTemplate retryableRestTemplate() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setReadTimeout(2000);
        clientHttpRequestFactory.setConnectTimeout(500);

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory) {
            @Override
            @Retryable(value = RestClientException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000)) // retry 3, delay 1000ms
            public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType)
                    throws RestClientException {
                return super.exchange(url, method, requestEntity, responseType);
            }

            //TODO - a-8. recover strategy
            /*
               application.yml에 debug:true 해놓고,
               아래처럼 하면, "bad request T.T" 가 로깅으로 찍힘과 동시에, retry fail하면, 페이지에 "bad request T.T"라고 보인다..
             */
            @Recover
            public <T> ResponseEntity<String> exchangeRecover(RestClientException e) {
                return ResponseEntity.badRequest().body("bad request T.T");
            }
        };

        return restTemplate;
    }
}