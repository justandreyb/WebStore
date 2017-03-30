package com.training.pages_creator.util;

import com.training.pages_creator.util.exception.UtilException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileReader {

    private static final String ROOT = "/WEB-INF/jsp/templates/forms/";

    public static String getFile(HttpServletRequest request, String fileName) throws UtilException {
        ServletContext context = request.getServletContext();
        String path = buildPath(fileName);
        try(InputStream stream = context.getResourceAsStream(path)) {
            return readFile(stream);
        } catch (IOException e) {
            throw new UtilException(e);
        }
    }

    private static String buildPath(String fileName) {
        StringBuilder path = new StringBuilder();

        path.append(ROOT);
        path.append(fileName);
        path.append(".jsp");

        return path.toString();
    }

    private static String readFile(InputStream stream) throws UtilException {
        try (BufferedInputStream inputStream = new BufferedInputStream(stream)) {
            int currentChar;
            StringBuilder file = new StringBuilder();

            while ((currentChar = inputStream.read()) != -1) {
                file.append((char) currentChar);
            }

            return file.toString();
        } catch (IOException e) {
            throw new UtilException(e);
        }
    }
}
