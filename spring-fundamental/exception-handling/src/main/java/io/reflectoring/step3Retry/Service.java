package io.reflectoring.step3Retry;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

@org.springframework.stereotype.Service
public class Service {

    //TODO - a-8. recover strategy on service
    @Retryable(RemoteAccessException.class) // 2
    public void service() {
        // ... do something
    }
    @Recover // 3
    public void recover(RemoteAccessException e) {
        // ... panic
    }
}
