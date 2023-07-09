package me.marcelberger.weatherapp.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailRequestData {
    @NotEmpty(message = "{mail.empty}")
    @Email(message = "{mail.invalid}")
    private String email;
}
