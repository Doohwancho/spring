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
//    private static final String STATIC_DIRECTORY = "/spring-fundamental/tomcat/src/main/resources/static";
    private static final String STATIC_DIRECTORY = "./tomcat/src/main/resources/static";

    public static String readResourceFile(String url) {
        try {
            String path = UrlParser.convertEmptyToHtml(url);

//            URL resource = IOUtils.class
//                    .getClassLoader()
//                    .getResource(STATIC_DIRECTORY + path); //여기서 에러난다. 왜지?
            URL resource = new URL("http","localhost", 8080,STATIC_DIRECTORY + path);

            validatePath(resource); //error: 경로가 잘못되었습니다!
            return new String(Files.readAllBytes(new File(resource.getFile()).toPath()));
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

