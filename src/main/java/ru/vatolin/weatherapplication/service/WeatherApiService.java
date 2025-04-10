package ru.vatolin.weatherapplication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vatolin.weatherapplication.dto.RawLocationDto;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class WeatherApiService {
    @Value("${weather.api.key}")
    private String keyApi;

    private final ObjectMapper objectMapper;

    public RawLocationDto createLocationDtoCity(String city) {
        String encodeCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String uri = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric".formatted(encodeCity, keyApi);
        return createRawLocationDto(uri);
    }

    public RawLocationDto createLocationDtoCoordinates(BigDecimal latitude, BigDecimal longitude) {
        String uri = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric".formatted(latitude, longitude, keyApi);
        return createRawLocationDto(uri);
    }

    private RawLocationDto createRawLocationDto(String uri) {
        RawLocationDto rawLocationDto = null;
        try(HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200) {
                rawLocationDto = objectMapper.readValue(response.body(), RawLocationDto.class);
            } else {
                throw new RuntimeException("Creation failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rawLocationDto;
    }
}
