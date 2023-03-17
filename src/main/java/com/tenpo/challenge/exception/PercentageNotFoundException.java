package com.tenpo.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class PercentageNotFoundException extends Exception {

    public PercentageNotFoundException() {
        super("Service Unavailable");
    }
}
