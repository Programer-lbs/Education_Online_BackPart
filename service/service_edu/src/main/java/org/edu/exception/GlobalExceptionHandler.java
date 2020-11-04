package org.edu.exception;

import org.edu.commonutil.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response exceptionHandler(Exception e){
        return Response.fail().message("服务器执行错误  "+e.getMessage());
    }

    @ExceptionHandler(TeacherException.class)
    @ResponseBody
    public Response teacherExceptionHandler(TeacherException e){
        return Response.fail().message(e.getMsg());
    }

    @ExceptionHandler(CourseException.class)
    @ResponseBody
    public Response courseExceptionHandler(CourseException e){
        return Response.fail().data("message",e.getMessage());
    }

    @ExceptionHandler(QueryException.class)
    @ResponseBody
    public Response queryExceptionHandler(QueryException e){
        return Response.fail().data("查询参数错误",e.getMessage());
    }

}
