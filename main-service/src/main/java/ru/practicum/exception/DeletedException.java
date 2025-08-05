package ru.practicum.exception;

public class DeletedException extends RuntimeException {

    public DeletedException(String message) {
        super(message);
    }

    public DeletedException(String entityName, String fieldName, Object value) {
        super(String.format("Entity restriction of removal %s with %s = '%s' - not empty", entityName, fieldName, value));
    }
}
