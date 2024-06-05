package com.cho.example.messageSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AppRunner4 implements ApplicationRunner {

    @Autowired
    MessageSource messageSource;

    public void run(ApplicationArguments args) throws Exception {
//        System.out.println(messageSource.getMessage("greeting", new String[]{"hello"}, Locale.KOREA));
    }

}
