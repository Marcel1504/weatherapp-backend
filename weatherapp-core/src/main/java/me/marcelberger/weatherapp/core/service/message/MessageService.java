package me.marcelberger.weatherapp.core.service.message;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public abstract class MessageService {

    public String get(String key) {
        return get(key, getLocale());
    }

    public String get(String key, String... args) {
        return get(key, LocaleContextHolder.getLocale(), args);
    }

    public String get(String key, Locale locale) {
        return getMessageSource().getMessage(key, null, locale);
    }

    public String get(String key, Locale locale, String... args) {
        return getMessageSource().getMessage(key, args, locale);
    }

    protected abstract MessageSource getMessageSource();

    protected abstract Locale getLocale();
}
