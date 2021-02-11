package com.app.nbbo.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ErrorBody implements Serializable {

    private String message;
    private int statusCode;

    public ErrorBody(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
