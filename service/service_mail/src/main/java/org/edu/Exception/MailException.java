package org.edu.Exception;

public class MailException extends RuntimeException {

    public MailException(){}

    public MailException(String message){
        super(message);
    }
}
