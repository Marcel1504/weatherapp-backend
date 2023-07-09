package me.marcelberger.weatherapp.api.service.message;

import java.util.Locale;

public interface MessageService {

    String get(String key);

    String get(String key, String... args);

    String get(String key, Locale locale);

    String get(String key, Locale locale, String... args);
}
