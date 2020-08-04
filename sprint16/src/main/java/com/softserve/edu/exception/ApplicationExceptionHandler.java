package com.softserve.edu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error-page", HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.addObject("info", ex.getMessage());
        modelAndView.addObject("status", "500");
        return modelAndView;
    }

	@ExceptionHandler(MarathonNotFoundException.class)
    public ModelAndView handleMarathonNotFoundException(MarathonNotFoundException exception){
        ModelAndView modelAndView = new ModelAndView("error-page-404", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("info", exception.getMessage());
        modelAndView.addObject("status", "404");
        return modelAndView;
    }
	
	@ExceptionHandler(StudentNotFoundException.class)
    public ModelAndView handleStudentNotFoundException(StudentNotFoundException exception){
        ModelAndView modelAndView = new ModelAndView("error-page-404", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("info", exception.getMessage());
        modelAndView.addObject("status", "404");
        return modelAndView;
    }
}
