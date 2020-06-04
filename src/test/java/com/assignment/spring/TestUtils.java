package com.assignment.spring;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Class for test util methods.
 */
public final class TestUtils {

    private TestUtils() {}

    /**
     * Return file content as string.
     * @param path path to file
     * @return file content as string
     */
    public static String getJsonFromFile(String path) {
        try {
            return IOUtils.toString(Objects.requireNonNull(TestUtils.class.getClassLoader().getResourceAsStream(path)), UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file : " + path, e);
        }
    }

}
