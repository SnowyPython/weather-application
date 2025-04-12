package ru.vatolin.weatherapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SearchLocationDto {
    private String city;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String codeCountry;
}
