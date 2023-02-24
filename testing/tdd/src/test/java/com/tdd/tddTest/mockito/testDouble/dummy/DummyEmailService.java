package com.tdd.tddTest.mockito.testDouble.dummy;

import com.tdd.tddTest.service.EmailService;

public class DummyEmailService implements EmailService {
    @Override
    public void sendEmail(String message) {
        throw new AssertionError("Method not implemented !!!");
    }
}
