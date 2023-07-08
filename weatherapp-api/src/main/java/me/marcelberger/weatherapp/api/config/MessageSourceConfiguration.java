package me.marcelberger.weatherapp.api.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

@Configuration
public class MessageSourceConfiguration {
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localResolver = new AcceptHeaderLocaleResolver();
        localResolver.setSupportedLocales(Arrays.asList(Locale.GERMAN, Locale.ENGLISH));
        localResolver.setDefaultLocale(Locale.ENGLISH);
        return localResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource res = new ResourceBundleMessageSource();
        res.setBasename("messages/messages");
        return res;
    }
}
