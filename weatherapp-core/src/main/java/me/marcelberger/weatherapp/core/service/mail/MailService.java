package me.marcelberger.weatherapp.core.service.mail;

import java.util.List;

public interface MailService {
    void sendMail(String to, String subject, String text);

    void sendMail(String to, String subject, String text, List<String> absoluteFilePaths);
}
