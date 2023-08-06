package me.marcelberger.weatherapp.consumer.config.security;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        StationEntity entity = stationRepository.findByCode(code);
        if (entity != null) {
            return User.builder()
                    .username(entity.getCode())
                    .password(entity.getApiKey())
                    .disabled(entity.getDisabled())
                    .authorities("STATION")
                    .build();
        } else {
            throw new UsernameNotFoundException(String.format("Station with code '%s' not found", code));
        }
    }
}
