package dev.rrlabs.hellorrlabs.config.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException (String messsage) {
        super(messsage);
    }
}
