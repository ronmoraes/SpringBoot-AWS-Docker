package com.hibecode.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class BeerNotFoundException extends BusinessException {

    public BeerNotFoundException() {
        super("generic-not-found", "Beer", HttpStatus.NOT_FOUND);
    }
}
