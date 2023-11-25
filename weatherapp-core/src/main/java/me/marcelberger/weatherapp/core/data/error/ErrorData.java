package me.marcelberger.weatherapp.core.data.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorData<CODE> {
    CODE code;
    String message;
}
