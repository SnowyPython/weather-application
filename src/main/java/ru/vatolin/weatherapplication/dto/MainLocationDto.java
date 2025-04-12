package ru.vatolin.weatherapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainLocationDto {
    private double temperature;
    private String city;
    private String codeCountry;
    private double feels;
    private String main;
    private String icon;
    private String humidity;
}
