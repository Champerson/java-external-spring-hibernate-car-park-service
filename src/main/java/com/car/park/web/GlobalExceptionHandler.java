package com.car.park.web;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    private static final String ERROR_PAGE = "error-page";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String onError(Exception exception, Model model) {

        if (exception instanceof SQLException) {
            handleException(exception);
            model.addAttribute("errorMessage", "error.database.failure");
        } else if (exception != null) {
            handleException(exception);
            model.addAttribute("errorMessage", "error.unknown");
        }

        return ERROR_PAGE;
    }

    private void handleException(Throwable e) {
        LOG.error(e.getMessage(), e);
    }
}
