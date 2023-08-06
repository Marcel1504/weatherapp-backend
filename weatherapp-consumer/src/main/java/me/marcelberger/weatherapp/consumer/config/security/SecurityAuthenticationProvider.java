package me.marcelberger.weatherapp.consumer.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String code = authentication.getName();
        String apiKey = authentication.getCredentials().toString();

        UserDetails user = userDetailsService.loadUserByUsername(code);
        if (user == null || !user.getUsername().equalsIgnoreCase(code)) {
            throw new UsernameNotFoundException(String.format("Station with code %s not found", code));
        }

        if (apiKey == null || !apiKey.equals(user.getPassword())) {
            throw new BadCredentialsException(String.format("Station with code %s has wrong API key", code));
        }

        return new UsernamePasswordAuthenticationToken(code, apiKey, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
