package com.greencode.petfinder.data.exception;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public class InvalidLocationException extends RuntimeException {

    public InvalidLocationException(String message) {
        super(message);
    }

}
