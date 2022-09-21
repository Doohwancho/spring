package org.apache.coyote.http11.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;

public class IOUtils {
    private static final Logger log = LoggerFactory.getLogger(IOUtils.class);

//    private static final String STATIC_DIRECTORY_METHOD1 = "./tomcat/src/main/resources/static";
    private static final String STATIC_DIRECTORY_METHOD2 = "static";

    public static String readResourceFile(String url) {
        try {
            String path = UrlParser.convertEmptyToHtml(url);
            log.info("path: "+path); //   index.html


            //방법0 - 원래 방법. 에러남!
//            URL resource = IOUtils.class
//                    .getClassLoader()
//                    .getResource(STATIC_DIRECTORY + path); //여기서 에러난다. 왜지?


            //방법1 - 절대경로 적어주기
//            URL resource = new URL("http","localhost", 8080,STATIC_DIRECTORY_METHOD1 + path);
//            validatePath(resource);
////            log.info("resource.getFile():   "+ resource.getFile()); // ./tomcat/src/main/resources/static/index.html
//            return new String(Files.readAllBytes(new File(resource.getFile()).toPath()));


//          //방법2 - classLoader를 이용해 /resources/static/을 찾는 방법.
          ClassLoader classLoader = IOUtils.class.getClassLoader();
          final String filePath = Objects.requireNonNull(classLoader.getResource(STATIC_DIRECTORY_METHOD2+path)).getPath();
          return new String(Files.readAllBytes(new File(filePath).toPath()));

        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            log.info(e.getMessage(), e);
        }
        throw new IllegalArgumentException("파일 읽기를 실패했습니다.");
    }

    private static void validatePath(URL resource) {
        log.info("validatePath안에서 resource url: "+ resource);
        if (Objects.isNull(resource)) {
            throw new IllegalArgumentException("경로가 잘못 되었습니다. : null");
        }
    }
}

