package ru.vatolin.weatherapplication.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SearchLocationDto {
    private String city;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String codeCountry;

    public void convert(RawLocationDto rawLocationDto) {
        this.city = rawLocationDto.getCity();
        this.latitude = rawLocationDto.getCoord().getLatitude();
        this.longitude = rawLocationDto.getCoord().getLongitude();
        this.codeCountry = rawLocationDto.getSys().getCodeCountry();
    }
}
