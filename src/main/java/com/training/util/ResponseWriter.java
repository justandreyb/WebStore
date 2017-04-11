package com.training.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseWriter {
    private static final Logger log = Logger.getLogger(ResponseWriter.class.getName());
    private static final String fatalError = "Cannot write to response";

    private static PrintWriter getPrintWriter(HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            log.fatal(fatalError, e);
        }
        return writer;
    }

    public static void write(HttpServletResponse response, String answer) {
        PrintWriter writer = getPrintWriter(response);
        if (writer != null) {
            writer.write(answer);
        }
    }

    public static void writeData(HttpServletResponse response, String answer) {
        answer = AnswerCreator.create(answer);
        write(response, answer);
    }

    public static void writeError(HttpServletResponse response, String answer) {
        answer = AnswerCreator.createError(answer);
        write(response, answer);
    }

}
