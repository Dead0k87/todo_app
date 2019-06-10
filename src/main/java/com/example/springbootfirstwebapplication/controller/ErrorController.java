package com.example.springbootfirstwebapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("error")
public class ErrorController {
//we can handlers for specific exceptions

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        // when Exception will happen, Controll will come here
        //populate error details and redirect to an error view
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", ex.getStackTrace());
        mv.addObject("url", request.getRequestURL()); // will give URL of the request
        mv.setViewName("error"); //error.jsp
        return mv;
    }
}
