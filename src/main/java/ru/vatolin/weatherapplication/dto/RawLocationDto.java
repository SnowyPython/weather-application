package ru.vatolin.weatherapplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawLocationDto {
    @JsonProperty("coord")
    private Coord coord;
    @JsonProperty("weather")
    private List<Weather> weathers;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("sys")
    private Sys sys;
    @JsonProperty("name")
    private String city;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Coord {
        @JsonProperty("lat")
        private BigDecimal latitude;
        @JsonProperty("lon")
        private BigDecimal longitude;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        @JsonProperty("main")
        private String main;
        @JsonProperty("icon")
        private String icon;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        @JsonProperty("temp")
        private double temperature;
        @JsonProperty("feels_like")
        private double feels;
        @JsonProperty("humidity")
        private String humidity;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sys {
        @JsonProperty("country")
        private String codeCountry;
    }

    public MainLocationDto convertToMainLocationDto() {
        MainLocationDto mainLocationDto = new MainLocationDto();

        mainLocationDto.setTemperature(this.getMain().getTemperature());
        mainLocationDto.setCity(this.getCity());
        mainLocationDto.setCodeCountry(this.getSys().getCodeCountry());
        mainLocationDto.setFeels(this.getMain().getFeels());
        mainLocationDto.setMain(this.getWeathers().getFirst().getMain());
        mainLocationDto.setIcon(this.getWeathers().getFirst().getIcon());
        mainLocationDto.setHumidity(this.getMain().getHumidity());

        return mainLocationDto;
    }

    public SearchLocationDto convertToSearchLocationDto() {
        SearchLocationDto searchLocationDto = new SearchLocationDto();

        searchLocationDto.setCity(this.getCity());
        searchLocationDto.setLatitude(this.getCoord().getLatitude());
        searchLocationDto.setLongitude(this.getCoord().getLongitude());
        searchLocationDto.setCodeCountry(this.getSys().getCodeCountry());

        return searchLocationDto;
    }
}
