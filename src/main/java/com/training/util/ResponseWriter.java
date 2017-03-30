package com.training.util;

import com.training.util.exception.ProjectUtilException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseWriter {

    private static PrintWriter getPrintWriter(HttpServletResponse response) throws ProjectUtilException {
        try {
            return response.getWriter();
        } catch (IOException e) {
            throw new ProjectUtilException(e);
        }
    }

    public static void write(HttpServletResponse response, String answer) throws ProjectUtilException {
        PrintWriter writer = getPrintWriter(response);
        writer.write(answer);
    }
}
