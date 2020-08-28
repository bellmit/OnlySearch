package application.service;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"application"})
//@ControllerAdvice 捕获全局异常
public class GloableErrorHandler {

    @ExceptionHandler(Exception.class)
    public String captureException(Exception e){
        return "error/error";
    }

}
