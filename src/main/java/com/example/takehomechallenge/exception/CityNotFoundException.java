package com.example.takehomechallenge.exception;

public class CityNotFoundException extends Throwable {
    public CityNotFoundException() {
        super();
    }

    public CityNotFoundException(String msg) {
        super(msg);
    }
}
