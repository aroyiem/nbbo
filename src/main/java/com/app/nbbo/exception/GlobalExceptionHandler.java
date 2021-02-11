package com.app.nbbo.exception;

import com.app.nbbo.model.ErrorBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Log logger = LogFactory.getLog(this.getClass());


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final ErrorBody handleError(final Exception e) {
        logger.error("Error occured " + e.getMessage());
        return new ErrorBody(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
