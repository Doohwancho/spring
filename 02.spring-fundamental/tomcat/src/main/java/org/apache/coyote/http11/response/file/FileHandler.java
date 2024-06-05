package org.apache.coyote.http11.response.file;

import org.apache.coyote.http11.Http11Processor;
import org.apache.coyote.http11.response.HttpResponseHeader;
import org.apache.coyote.http11.response.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.apache.coyote.http11.util.HttpStatus.OK;

public class FileHandler {

    private static final Logger log = LoggerFactory.getLogger(FileHandler.class);

    public static boolean isStaticFilePath(final String resource) {
        final URL url = FileHandler.class.getClassLoader().getResource(resource.substring(1));
        return url != null && resource.contains(".");
    }

    public static ResponseEntity createFileResponse(final String resource) throws IOException {
        final URL url = FileHandler.class.getClassLoader().getResource(resource.substring(1));
        final Path path = Path.of(url.getPath());
        final byte[] fileBytes = Files.readAllBytes(path);

        return new ResponseEntity(OK, getFileHttpHeader(path), new String(fileBytes));
    }

    private static HttpResponseHeader getFileHttpHeader(final Path path) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", Files.probeContentType(path));

        return new HttpResponseHeader(headers);
    }
}
