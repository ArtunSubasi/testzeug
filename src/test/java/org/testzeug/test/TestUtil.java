package org.testzeug.test;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TestUtil {
    
    public static String resourceToString(String path) throws IOException {
        return IOUtils.resourceToString(path, StandardCharsets.UTF_8, TestUtil.class.getClassLoader());
    }
    
}
