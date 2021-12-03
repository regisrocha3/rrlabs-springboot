package dev.rrlabs.hellorrlabs.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ApiError> handleValidationException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().stream().map(f ->
                ApiError.builder().message(f.getDefaultMessage())
                        .field(f.getField()).build()).collect(Collectors.toList());

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiError handleError(Exception e) {
        log.error("Ocorreu um erro ao processar a requisicao: ", e);
        return ApiError.builder().message("Ocorreu um erro inesperado, por favor tente mais tarde. =/").build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiError handleError(NotFoundException e) {
        return ApiError.builder().message("Recurso nao foi encontrado").build();
    }

}
