package com.example.learn.recipes_app.controller;

import com.example.learn.recipes_app.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleNumberFormatException(NumberFormatException exception) {
        log.error("The request was not specified correctly.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/nfe");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("The recipe was not found, returning an error page.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

}
