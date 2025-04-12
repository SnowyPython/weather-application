package ru.vatolin.weatherapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vatolin.weatherapplication.dto.MainLocationDto;
import ru.vatolin.weatherapplication.dto.RawLocationDto;
import ru.vatolin.weatherapplication.dto.SearchLocationDto;
import ru.vatolin.weatherapplication.dto.UserDto;
import ru.vatolin.weatherapplication.entity.Location;
import ru.vatolin.weatherapplication.entity.User;
import ru.vatolin.weatherapplication.repository.UserRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WeatherApiService apiService;
    private final LocationService locationService;

    public boolean userIsExist(String login) {
        User user = userRepository.findByLogin(login).orElse(null);

        return user != null;
    }

    public void registerUser(UserDto userDto) {
        String password = passwordEncoder.encode(userDto.getPassword());

        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(password);

        userRepository.save(user);
    }

    public Set<MainLocationDto> getLocations(String login) {
        User user = userRepository.findByLogin(login).orElse(null);
        Set<Location> locations = null;
        if(user != null) {
            locations = user.getLocations();
        }
        Set<MainLocationDto> mainLocationDtos = new HashSet<>();
        if(locations != null) {
            for (Location location : locations) {
                RawLocationDto rawLocationDto = apiService.createLocationDtoCity(location.getName());

                MainLocationDto mainLocationDto = rawLocationDto.convertToMainLocationDto();

                mainLocationDtos.add(mainLocationDto);
            }
        }

        if(user != null) return mainLocationDtos;
        return null;
    }

    public void addLocationForUser(BigDecimal latitude, BigDecimal longitude, String login) {
        RawLocationDto rawLocationDto = apiService.createLocationDtoCoordinates(latitude, longitude);
        User user = userRepository.findByLogin(login).orElse(null);

        if(user != null) {
            locationService.saveLocation(user, rawLocationDto);
        }
    }

    public String getFormatedAuthenticatedLogin() {
        String login = getAuthenticatedLogin();
        String[] array = login.split("@");
        return array[0];
    }

    public String getAuthenticatedLogin() {
        return getAuthenticatedUser().getLogin();
    }

    private User getAuthenticatedUser() {
        String login = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            login = authentication.getName();
        }

        return userRepository.findByLogin(login).orElse(null);
    }
}
