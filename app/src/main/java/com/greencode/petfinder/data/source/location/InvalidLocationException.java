package com.greencode.petfinder.data.source.location;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public class InvalidLocationException extends RuntimeException {

    public InvalidLocationException(String message) {
        super(message);
    }

}
