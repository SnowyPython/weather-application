package ru.vatolin.weatherapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vatolin.weatherapplication.dto.RawLocationDto;
import ru.vatolin.weatherapplication.dto.SearchLocationDto;
import ru.vatolin.weatherapplication.entity.Location;
import ru.vatolin.weatherapplication.entity.User;
import ru.vatolin.weatherapplication.repository.LocationRepository;
import ru.vatolin.weatherapplication.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final WeatherApiService apiService;

    public List<SearchLocationDto> getSearchedLocations(String city) {
        List<SearchLocationDto> list = new ArrayList<>();

        RawLocationDto rawLocationDto = apiService.createLocationDtoCity(city);

        SearchLocationDto searchLocationDto = new SearchLocationDto();
        searchLocationDto.convert(rawLocationDto);

        list.add(searchLocationDto);
        return list;
    }

    public void saveLocation(User user, RawLocationDto rawLocationDto) {
        Location location = new Location();
        location.setName(rawLocationDto.getCity());
        location.setLatitude(rawLocationDto.getCoord().getLatitude());
        location.setLongitude(rawLocationDto.getCoord().getLongitude());

        user.addLocation(location);
    }
}
