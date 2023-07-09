package me.marcelberger.weatherapp.api.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String get(String key) {
        return get(key, LocaleContextHolder.getLocale());
    }

    @Override
    public String get(String key, String... args) {
        return get(key, LocaleContextHolder.getLocale(), args);
    }

    @Override
    public String get(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    @Override
    public String get(String key, Locale locale, String... args) {
        return messageSource.getMessage(key, args, locale);
    }
}
