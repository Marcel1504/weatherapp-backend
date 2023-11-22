package me.marcelberger.weatherapp.core.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import me.marcelberger.weatherapp.core.data.error.ErrorData;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;

@ControllerAdvice
public class CoreExceptionHandler {

    @Autowired
    private Validator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ExceptionHandler(CoreException.class)
    public ResponseEntity<ErrorData> handleServiceException(CoreException e) {
        return statusDataResponseBadRequest(e.getMessage(), e.getCode(), e.getProperties());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorData> handleConstrainViolationException(ConstraintViolationException e) {
        List<String> list = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        return statusDataResponseBadRequest(e.getMessage(), ErrorCodeEnum.CODE00001, list);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorData> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> list = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return statusDataResponseBadRequest(e.getMessage(), ErrorCodeEnum.CODE00001, list);
    }

    private ResponseEntity<ErrorData> statusDataResponseBadRequest(String message,
                                                                   ErrorCodeEnum code,
                                                                   List<String> properties) {
        ErrorData data = ErrorData.builder()
                .message(message)
                .code(code)
                .properties(properties)
                .build();
        return ResponseEntity.badRequest().body(data);
    }
}
