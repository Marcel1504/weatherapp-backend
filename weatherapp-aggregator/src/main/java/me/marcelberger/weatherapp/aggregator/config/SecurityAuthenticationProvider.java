package me.marcelberger.weatherapp.aggregator.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    @Value("${weatherapp.authentication.username}")
    private String username;

    @Value("${weatherapp.authentication.password}")
    private String password;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String authUsername = authentication.getName();
        String authPassword = authentication.getCredentials().toString();
        if (authUsername == null || !authUsername.equals(username)) {
            throw new UsernameNotFoundException(String.format("User %s not found", authUsername));
        }
        if (authPassword == null || !authPassword.equals(password)) {
            throw new BadCredentialsException("User has wrong password");
        }
        return new UsernamePasswordAuthenticationToken(authPassword, authPassword, List.of(() -> "USER"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
