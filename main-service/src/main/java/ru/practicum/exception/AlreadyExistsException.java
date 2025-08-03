package ru.practicum.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String entityName, String fieldName, String value) {
        super(String.format("%s with %s = '%s' already exists", entityName, fieldName, value));
    }
}
