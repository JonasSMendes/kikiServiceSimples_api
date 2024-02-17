package com.example.servicoEntregaKiki.exception;

public class NotFoundOrderServiceException extends RuntimeException {

    public NotFoundOrderServiceException(String message) {
        super(message);
    }

    public NotFoundOrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
