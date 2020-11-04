package org.edu.exception;

public class CourseException extends RuntimeException{

    public CourseException(){}

    public CourseException(String message){
        super(message);
    }
}
