package com.epam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resources Not Found")
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Integer id) {
        super("resource id = " + id);
    }

    public ResourceNotFoundException(String id) {
        super("resource email = " + id);
    }
}
