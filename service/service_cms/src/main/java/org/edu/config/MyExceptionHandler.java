package org.edu.config;

import org.edu.commonutil.Response;
import org.edu.exception.BannerException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response ExceptionHandler(Exception e){
        return Response.fail().data("error",e.getMessage());
    }

    @ExceptionHandler(BannerException.class)
    @ResponseBody
    public Response bannerExceptionHandler(BannerException e){
        return Response.fail().data("error",e.getMessage());
    }
}
