package me.marcelberger.weatherapp.core.data.error;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;

import java.util.List;

@Data
@Builder
public class ErrorData {
    private String message;
    private ErrorCodeEnum code;
    private List<String> properties;
}
