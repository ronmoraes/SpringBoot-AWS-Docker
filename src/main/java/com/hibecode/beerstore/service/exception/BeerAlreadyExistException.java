package com.hibecode.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class BeerAlreadyExistException extends BusinessException {

    public BeerAlreadyExistException() {
        super("generic-already-exists", "Beer", HttpStatus.BAD_REQUEST);
    }
}
