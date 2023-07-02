package me.marcelberger.weatherapp.core.exception;

import me.marcelberger.weatherapp.core.data.StatusData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<StatusData> handleServiceException(ServiceException e) {
        return statusDataResponseBadRequest(e.getMessage());
    }

    private ResponseEntity<StatusData> statusDataResponseBadRequest(String message) {
        StatusData data = StatusData.builder()
                .message(message)
                .build();
        return ResponseEntity.badRequest().body(data);
    }
}
