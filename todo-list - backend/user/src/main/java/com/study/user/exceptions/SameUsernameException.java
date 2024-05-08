package com.study.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SameUsernameException extends RuntimeException {

    public SameUsernameException(){
        super("Username already registered");
    }
}
