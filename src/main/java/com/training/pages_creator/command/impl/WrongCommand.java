package com.training.pages_creator.command.impl;

import com.training.pages_creator.command.Command;
import com.training.pages_creator.util.FileReader;
import com.training.pages_creator.util.exception.UtilException;
import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.util.exception.ProjectUtilException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WrongCommand implements Command {
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String result = FileReader.getFile(request, "add-brand");
            result = AnswerCreator.create(result);

            ResponseWriter.write(response, result);
        } catch (UtilException | ProjectUtilException e) {
            System.out.println("Smt went wrong while getting file");
            //TODO: Write error
        }
    }
}
