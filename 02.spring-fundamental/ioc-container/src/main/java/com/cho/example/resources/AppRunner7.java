package com.cho.example.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Component
public class AppRunner7 implements ApplicationRunner {

    @Autowired
    ApplicationContext resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //case1) 웹상 자원 가져오는 코드
//        URL url = new URL("https://www.naver.com");
//        URLConnection urlConnection = url.openConnection();
//        InputStream is = urlConnection.getInputStream();
//        InputStreamReader isr = new InputStreamReader(is);
//        BufferedReader br = new BufferedReader(isr);
//
//        String temp = "";
//
//        while(true){
//            temp = br.readLine();
//            if(temp == null) break;
//            System.out.println(temp);
//        }

        //case2) resources/ 에 있는 static file 가져오는 법
        Resource resource = resourceLoader.getResource("classpath:test.txt");
        if(resource.exists()){
            System.out.println(resource.getDescription());

            InputStream is = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String temp = "";

            while(true){
                temp = br.readLine();
                if(temp == null) break;
                System.out.println(temp);
            }
        }

        //console.log
//        class path resource [test.txt]
//        hello world
    }

}
