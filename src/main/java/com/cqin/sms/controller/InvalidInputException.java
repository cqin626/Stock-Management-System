package com.cqin.sms.controller;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String str) {
        super(str);
    }
}
