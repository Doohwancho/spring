package com.cho.example.beanScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    Single single;

    @Autowired
    Proto proto;

    @Autowired
    Helper helper;

    @Autowired
    ApplicationContext ctx;


    public void run(ApplicationArguments args) throws Exception {
        /*************************************
         /*  case1) beanscope - singleton scope
         /*  Single의 객체는 한개만 생성된다.
         System.out.println(single);
         System.out.println(Helper.getSingle());

         //console.log
         //com.cho.example.beanScope.Single@30ca0779 //주소가 같다!
         //com.cho.example.beanScope.Single@30ca0779 //주소가 같다!
         */


        /*************************************
         * case2) beanscope - prototype scope
         * Bean을 IoC 컨테이너한테 받아올 떄마다 새로운 객체 생성

         System.out.println(proto);
         System.out.println(helper.getProto());

         //console.log
         //com.cho.example.beanScope.Proto@5de6cf3a //서로 주소가 다르다! bean 객체가 같은데!
         //com.cho.example.beanScope.Proto@4cc36c19 //서로 주소가 다르다!
         */


        /*************************************
         * case3) beanscope - prototype from application context
         * Bean을 IoC 컨테이너한테 받아올 떄마다 새로운 객체 생성

         System.out.println(ctx.getBean("proto")); //com.cho.example.beanScope.Proto@11900483 //서로 주소가 다르다!
         System.out.println(ctx.getBean("proto")); //com.cho.example.beanScope.Proto@14a049f9
         System.out.println(ctx.getBean("proto")); //com.cho.example.beanScope.Proto@94e51e8
         */


        /*************************************
         * case4) single scope에서 proto scope 가져오면 객체 새로 만들어지나?

         System.out.println(ctx.getBean(Single.class).getProto()); //com.cho.example.beanScope.Proto@56cfe111 // singleton에서 proto 가져왔는데 똑같다?!
         System.out.println(ctx.getBean(Single.class).getProto()); //com.cho.example.beanScope.Proto@56cfe111
         System.out.println(ctx.getBean(Single.class).getProto()); //com.cho.example.beanScope.Proto@56cfe111

         //Q. 왜 beanscope-single 에서 가져온 beanscope-proto는 같은 놈을 가져옴?
         //A. IoC 컨테이너가 Single Bean 만들 때, proto를 가르키던 포인터 그대로 가져오는 것.
         // IoC 컨테이너로 부터 proto bean을 가져온다고 요청하는게 아니라, 이미 선언된 singleton 가져오는거라 그럼.
         */

        /*************************************
         * case5) single scope에서 proto scope 가져오면 객체 새로 만들어지나?


        System.out.println(ctx.getBean(Single.class).getProto()); //com.cho.example.beanScope.Proto@76332405 // singleton에서 proto 가져왔는데 이번엔 다르다?!
        System.out.println(ctx.getBean(Single.class).getProto()); //com.cho.example.beanScope.Proto@187e5235
        System.out.println(ctx.getBean(Single.class).getProto()); //com.cho.example.beanScope.Proto@d1d8e1a

        //Q. 왜 이번엔 singlton에서 proto 가져와도 객체 주소가 다르지?
        //A. @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
        // 프록시 써서 Proto 반환될 때 새 객체 만들어서 반환함
         */
    }
}
