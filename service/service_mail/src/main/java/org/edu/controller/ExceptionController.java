package org.edu.controller;


import org.edu.Exception.MailException;
import org.edu.commonutil.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MailException.class)
    @ResponseBody
    public Response mailExceptionHandler(MailException e){
        return Response.fail().data("error",e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response ExceptionHandler(Exception e){
        System.out.println(e.getMessage());
        return Response.fail().data("error","服务器出错啦！");
    }
}
