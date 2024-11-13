package com.consultancy.reservations.application.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String msj) {
        super(msj);
    }
}
