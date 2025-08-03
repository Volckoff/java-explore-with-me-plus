package ru.practicum.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
@SuppressWarnings("unused")
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFound(NotFoundException e) {
        log.warn("Сущность не найдена: {}", e.getMessage());
        return new ErrorResponse(
                "NOT_FOUND",
                "Запрашиваемый объект не найден.",
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEntityAlreadyExists(AlreadyExistsException e) {
        log.warn("Сущность уже существует: {}", e.getMessage());
        return new ErrorResponse(
                "CONFLICT",
                "Нарушено ограничение целостности данных.",
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.warn("Ошибка валидации: {}", message);
        return new ErrorResponse(
                "BAD_REQUEST",
                "Переданы некорректные данные.",
                message,
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnexpectedException(Exception e) {
        log.error("Внутренняя ошибка сервера", e); // Логируем стек-трейс
        return new ErrorResponse(
                "INTERNAL_ERROR",
                "Внутренняя ошибка сервера.",
                "Произошла непредвиденная ошибка. Администратор уже уведомлён.",
                LocalDateTime.now()
        );
    }
}
