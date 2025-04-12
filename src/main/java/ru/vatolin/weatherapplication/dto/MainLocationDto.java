package ru.vatolin.weatherapplication.dto;

import lombok.Getter;

@Getter
public class MainLocationDto {
    private double temperature;
    private String city;
    private String codeCountry;
    private double feels;
    private String main;
    private String icon;
    private String humidity;

    public void convert(RawLocationDto rawLocationDto) {
        this.temperature = rawLocationDto.getMain().getTemperature();
        this.city = rawLocationDto.getCity();
        this.codeCountry = rawLocationDto.getSys().getCodeCountry();
        this.feels = rawLocationDto.getMain().getFeels();
        this.main = rawLocationDto.getWeathers().getFirst().getMain();
        this.icon = rawLocationDto.getWeathers().getFirst().getIcon();
        this.humidity = rawLocationDto.getMain().getHumidity();
    }
}
