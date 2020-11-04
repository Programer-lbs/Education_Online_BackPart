package org.edu.exception;

public class BannerException extends RuntimeException{
    public BannerException(){}

    public BannerException(String message){
        super(message);
    }
}
