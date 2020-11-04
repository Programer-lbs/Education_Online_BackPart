package org.edu.exception;

import org.edu.commonutil.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MemberException.class)
    @ResponseBody
    public Response memberExceptionHandler(MemberException e){
        return Response.fail().data("error",e.getMessage());
    }
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Response exceptionHandler(MemberException e){
//        return Response.fail().data("error",e.getMessage());
//    }
}
