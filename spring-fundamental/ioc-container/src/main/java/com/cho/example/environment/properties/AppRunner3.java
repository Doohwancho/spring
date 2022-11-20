package com.cho.example.environment.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:app.properties")
public class AppRunner3 implements ApplicationRunner {

    @Autowired
    ApplicationContext applicationContext;

    @Value("${app.name}") String name;

    public void run(ApplicationArguments args) throws Exception {

        //case1) Environment에 외부 파일 이용해 Property 추가하기
//        ConfigurableEnvironment env = (ConfigurableEnvironment)applicationContext.getEnvironment();
//        MutablePropertySources propertySources = env.getPropertySources();
//        propertySources.addLast(new ResourcePropertySource("classpath:app.properties"));
//        System.out.println(env.getProperty("app.name")); //"hello"
//        System.out.println(env.getProperty("app.pw")); //"1234"



        //case2) @PropertySource("classpath:app.properties") 을 붙여서 app.properties에서  간편하게 가져오기
//        Environment env = applicationContext.getEnvironment();
//        System.out.println(env.getProperty("app.name")); //"hello"
//        System.out.println(env.getProperty("app.pw")); //"1234"


        //case3) @Value로 app.properties에서 간편하게 가져오기
//        System.out.println(name); //"hello"

        //case4) jvm option 이용하기
        //VM options: -Dapp.name=hello
//        System.out.println(name); //"hello"
    }

}
