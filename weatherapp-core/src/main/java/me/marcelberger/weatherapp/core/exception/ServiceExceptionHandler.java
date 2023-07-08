package me.marcelberger.weatherapp.core.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import me.marcelberger.weatherapp.core.data.StatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;

@ControllerAdvice
public class ServiceExceptionHandler {

    @Autowired
    private Validator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<StatusData> handleServiceException(ServiceException e) {
        return statusDataResponseBadRequest(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StatusData> handleConstrainViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        List<ConstraintViolation<?>> list = e.getConstraintViolations().stream().toList();
        for (int i = 0; i < list.size(); i++) {
            message.append(list.get(i).getMessage());
            if (i < list.size() - 1) {
                message.append(", ");
            }
        }
        return statusDataResponseBadRequest(message.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StatusData> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        List<ObjectError> list = e.getBindingResult().getAllErrors();
        for (int i = 0; i < list.size(); i++) {
            message.append(list.get(i).getDefaultMessage());
            if (i < list.size() - 1) {
                message.append(", ");
            }
        }
        return statusDataResponseBadRequest(message.toString());
    }

    private ResponseEntity<StatusData> statusDataResponseBadRequest(String message) {
        StatusData data = StatusData.builder()
                .message(message)
                .build();
        return ResponseEntity.badRequest().body(data);
    }
}
