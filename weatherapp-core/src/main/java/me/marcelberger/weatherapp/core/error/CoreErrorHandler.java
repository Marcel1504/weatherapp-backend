package me.marcelberger.weatherapp.core.error;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.core.data.error.ErrorData;
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
@Slf4j
public class CoreErrorHandler {

    @Autowired
    private Validator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ExceptionHandler(CoreError.class)
    public ResponseEntity<ErrorData<CoreError.Code>> handleCoreError(CoreError e) {
        log.warn("CoreError(code={}): {}", e.getCode().getValue(), e.getMessage());
        return ResponseEntity.badRequest().body(ErrorData.<CoreError.Code>builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorData<CoreError.Code>> handleConstrainViolationException(ConstraintViolationException e) {
        log.warn("CoreError(code={}): {}", CoreError.Code.CORE00001.getValue(), e.getMessage());
        List<String> list = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        return ResponseEntity.badRequest().body(ErrorData.<CoreError.Code>builder()
                .code(CoreError.Code.CORE00001)
                .message(String.format("Constraints violated for %s", list))
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorData<CoreError.Code>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("CoreError(code={}): {}", CoreError.Code.CORE00001.getValue(), e.getMessage());
        List<String> list = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(ErrorData.<CoreError.Code>builder()
                .code(CoreError.Code.CORE00001)
                .message(String.format("Method arguments not valid for %s", list))
                .build());
    }
}
