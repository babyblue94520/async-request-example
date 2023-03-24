package com.example.asyncrequestexample.infrastructure.exception;

import java.util.concurrent.RejectedExecutionException;

public class StringRejectedExecutionException extends RejectedExecutionException {

    public StringRejectedExecutionException(String message) {
        super(message);
    }
}
