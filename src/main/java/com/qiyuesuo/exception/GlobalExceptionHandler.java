package com.qiyuesuo.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Auth0r : fangbofei
 * Date : 2018/8/2/0002
 * Time : 17:38
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileSystemException.class)
    public String handleException(FileSystemException e, Model model) {
        model.addAttribute("exception", e.getMessage());
        return "exception";
    }

}
