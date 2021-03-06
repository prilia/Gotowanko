package pl.edu.uj.gotowanko.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.edu.uj.gotowanko.util.LogRequestFilter;

@ControllerAdvice
public class LoggingExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(LogRequestFilter.class.getSimpleName());

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.info("Returning HTTP 400 Bad Request", e);
        throw e;
    }
}