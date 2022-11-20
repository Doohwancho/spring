package com.cho.example.environment.profile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AppRunner2 implements ApplicationRunner {

    @Autowired
    ApplicationContext applicationContext;

//    @Autowired
//    Service service;

    public void run(ApplicationArguments args) throws Exception {
        /**************************************
         //case1) 기본 env
         Environment env = applicationContext.getEnvironment();
         System.out.println(Arrays.toString(env.getDefaultProfiles())); // [default] //default profile엔 @Bean 들 중에서 @Profile 안붙은 애들이 모두 여기에 들어감.
         System.out.println(Arrays.toString(env.getActiveProfiles())); // [ ]

         */
        /**************************************
         //case2) 실행시 env 옵션에 profile 넣어 실행
         //VM options: -Dspring.profiles.active="test"

         Environment env = applicationContext.getEnvironment();
         System.out.println(Arrays.toString(env.getDefaultProfiles())); // [default] //default profile엔 @Bean 들 중에서 @Profile 안붙은 애들이 모두 여기에 들어감.
         System.out.println(Arrays.toString(env.getActiveProfiles())); // [test] // RandomService에 @Profile붙이니 여기에 걸림
         */

        /**************************************
         //case3) @Profile 에 "!test" 해보기(&,|도 가능)
         //VM options: -Dspring.profiles.active="test"

        Environment env = applicationContext.getEnvironment();
        System.out.println(Arrays.toString(env.getDefaultProfiles())); // [default] //default profile엔 @Bean 들 중에서 @Profile 안붙은 애들이 모두 여기에 들어감.
        System.out.println(Arrays.toString(env.getActiveProfiles())); // [test] // RandomService에 @Profile붙이니 여기에 걸림
//         System.out.println(service); //@Profile("!test") // ERROR!: Field service in com.cho.example.environment.AppRunner2 required a bean of type 'com.cho.example.environment.Service' that could not be found.
         */
    }
}