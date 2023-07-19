package com.cheese.springjpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    //case1) minimal
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }



    //case2) more configs
    //api 서버에서 swagger 입힐 때, 이 api의 버전은 몇이고, 목적이 뭐고, 라이센스 뭐고 등 설명 달 떄 쓰는 config

//    private String version = "V1";
//    private String title = "Cristoval GuestBook API " + version;
//
//    @Bean
//    public Docket api() {
//        List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
//        responseMessages.add(new ResponseMessageBuilder().code(200).message("OK !!!").build());
//        responseMessages.add(new ResponseMessageBuilder().code(500).message("서버 문제 발생 !!!").responseModel(new ModelRef("Error")).build());
//        responseMessages.add(new ResponseMessageBuilder().code(404).message("페이지를 찾을 수 없습니다 !!!").build());
//
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName(version).select()
//                .apis(RequestHandlerSelectors.basePackage("com.cristoval.web.controller"))
//                .paths(postPaths()).build()
//                .useDefaultResponseMessages(false) // responseMessages 설정 적용
//                .globalResponseMessage(RequestMethod.GET,responseMessages);
//    }

//    private Predicate<String> postPaths() {
////      return PathSelectors.any(); // 모든 경로를 api 문서로 만들경우
////      return or(regex("/admin/.*"), regex("/user/.*"));  // 일부 경로를 api 문서로 만들 경우
//        return regex("/admin/.*");
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title(title)
//                .description("<h3>CRISTOVAL API Reference for Developers</h3>Swagger를 이용한 GuestBook API<br><img src=\"imgName.png\">")
//                .contact(new Contact("CRISTOVAL", "https://cistoval.com", "cristoval@gmail.com"))
//                .license("CRISTOVAL License")
//                .licenseUrl("https://www.cristoval.com/etc/webPrivacy.jsp")
//                .version("1.0").build();
//    }


    //case3) 기존 version1 위에 version2 덧붙이고 싶다면?
//    @Bean
//    public Docket api2() {
//        version = "V2";
//        List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
//        responseMessages.add(new ResponseMessageBuilder().code(200).message("OK !!!").build());
//        responseMessages.add(new ResponseMessageBuilder().code(500).message("서버 문제 발생 !!!").responseModel(new ModelRef("Error")).build());
//        responseMessages.add(new ResponseMessageBuilder().code(404).message("페이지를 찾을 수 없습니다 !!!").build());
//
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo2()).groupName(version).select()
//                .apis(RequestHandlerSelectors.basePackage("com.cristoval.web.controller"))
//                .paths(postPaths()).build()
//                .useDefaultResponseMessages(false) // responseMessages 설정 적용
//                .globalResponseMessage(RequestMethod.GET,responseMessages);
//    }


//    private ApiInfo apiInfo2() {
//        return new ApiInfoBuilder().title(title)
//                .description("<h3>CRISTOVAL API Reference for Developers</h3>Swagger를 이용한 GuestBook API<br><img src=\"imgName.png\">")
//                .contact(new Contact("CRISTOVAL", "https://cistoval.com", "cristoval@gmail.com"))
//                .license("CRISTOVAL License")
//                .licenseUrl("https://www.cristoval.com/etc/webPrivacy.jsp")
//                .version("2.0").build();
//    }

}
