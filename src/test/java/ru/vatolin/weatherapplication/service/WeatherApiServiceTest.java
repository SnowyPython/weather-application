package ru.vatolin.weatherapplication.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class WeatherApiServiceTest {
    @Autowired
    private WeatherApiService apiService;

    @Test
    public void createLocationDtoTest() {
        Assertions.assertEquals("Moscow", apiService.createLocationDtoCity("Moscow").getCity());
        Assertions.assertEquals("New York", apiService.createLocationDtoCity("New York").getCity());
        Assertions.assertEquals("Tokyo", apiService.createLocationDtoCity("Tokyo").getCity());
        Assertions.assertEquals("Berlin", apiService.createLocationDtoCity("Berlin").getCity());
        Assertions.assertEquals("Ekaterinburg", apiService.createLocationDtoCity("Екатеринбург").getCity());
    }

    @Test
    public void createLocationDtoCoordinatesTest() {
        Assertions.assertEquals("Moscow", apiService.createLocationDtoCoordinates(BigDecimal.valueOf(55.7522), BigDecimal.valueOf(37.6156)).getCity());
        Assertions.assertEquals("Berlin", apiService.createLocationDtoCoordinates(BigDecimal.valueOf(52.5244), BigDecimal.valueOf(13.4105)).getCity());
        Assertions.assertEquals("San Francisco", apiService.createLocationDtoCoordinates(BigDecimal.valueOf(37.7749), BigDecimal.valueOf(-122.4194)).getCity());
    }
}
