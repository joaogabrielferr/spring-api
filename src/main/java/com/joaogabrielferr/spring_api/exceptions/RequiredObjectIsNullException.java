package com.joaogabrielferr.spring_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public RequiredObjectIsNullException(String ex){
        super(ex);
    }

    public RequiredObjectIsNullException(){
        super("Can't persist a null object!");
    }



}
