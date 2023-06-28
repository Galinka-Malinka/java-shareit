package ru.practicum.shareit.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
