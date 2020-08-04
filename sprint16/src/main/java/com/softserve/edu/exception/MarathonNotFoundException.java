package com.softserve.edu.exception;

public class MarathonNotFoundException extends RuntimeException {
	
    public MarathonNotFoundException() {
    }

    public MarathonNotFoundException(String message) {
        super(message);
    }
}