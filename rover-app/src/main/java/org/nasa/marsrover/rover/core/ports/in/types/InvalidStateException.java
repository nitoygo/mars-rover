package org.nasa.marsrover.rover.core.ports.in.types;

public class InvalidStateException extends RuntimeException {
    public InvalidStateException(String message) {
        super(message);
    }
}
