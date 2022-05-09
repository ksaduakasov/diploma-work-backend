package com.example.diplomawork.exception;

public class SpringAppException extends RuntimeException {

    public SpringAppException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }


    public SpringAppException(String exMessage) {
        super(exMessage);
    }

}
