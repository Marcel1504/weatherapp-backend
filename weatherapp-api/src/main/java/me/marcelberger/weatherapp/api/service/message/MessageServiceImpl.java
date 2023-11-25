package me.marcelberger.weatherapp.api.service.message;

import me.marcelberger.weatherapp.core.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImpl extends MessageService {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected MessageSource getMessageSource() {
        return messageSource;
    }

    @Override
    protected Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}
