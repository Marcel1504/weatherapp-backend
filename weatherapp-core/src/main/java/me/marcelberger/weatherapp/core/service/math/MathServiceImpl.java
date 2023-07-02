package me.marcelberger.weatherapp.core.service.math;

import org.springframework.stereotype.Service;

@Service
public class MathServiceImpl implements MathService {
    @Override
    public Double round(Double value, Integer precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
