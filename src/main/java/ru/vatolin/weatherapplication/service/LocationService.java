package ru.vatolin.weatherapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vatolin.weatherapplication.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final UserRepository userRepository;
    private final WeatherApiService apiService;
}
