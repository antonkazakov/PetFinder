package com.greencode.petfinder.data.exception;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public class LocationUnavailableException extends RuntimeException {

    public LocationUnavailableException(String message) {
        super(message);
    }

}
