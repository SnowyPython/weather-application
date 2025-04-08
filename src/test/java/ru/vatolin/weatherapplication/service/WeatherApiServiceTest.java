package ru.vatolin.weatherapplication.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherApiServiceTest {
    @Autowired
    private WeatherApiService apiService;

    @Test
    public void createLocationDtoTest() {
        Assertions.assertEquals("Moscow", apiService.createLocationDto("Moscow").getCity());
        Assertions.assertEquals("New York", apiService.createLocationDto("New York").getCity());
        Assertions.assertEquals("Tokyo", apiService.createLocationDto("Tokyo").getCity());
        Assertions.assertEquals("Berlin", apiService.createLocationDto("Berlin").getCity());
        Assertions.assertEquals("Ekaterinburg", apiService.createLocationDto("Екатеринбург").getCity());
    }
}
