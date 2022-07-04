package com.vesmer.familytestbackend.exception;

public class FamilyException extends RuntimeException{
    public FamilyException(String exMessage, Exception exception){
        super(exMessage, exception);
    }

    public FamilyException(String exMessage){
        super(exMessage);
    }
}
