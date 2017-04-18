package com.training.web_store.command.impl.user;

import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.account.User;
import com.training.web_store.command.impl.UserCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAccountsCommand extends UserCommand {
    private static final String ERROR_MESSAGE = "Something went wrong while getting accounts";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> users = service.getAccounts();
            String accountsJSON = AnswerCreator.createJSONFromAccounts(users);

            ResponseWriter.writeData(response, accountsJSON);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
