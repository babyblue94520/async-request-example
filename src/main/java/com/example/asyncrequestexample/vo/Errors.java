package com.example.asyncrequestexample.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Errors {
    private final Boolean status;
    private final String message;

}
