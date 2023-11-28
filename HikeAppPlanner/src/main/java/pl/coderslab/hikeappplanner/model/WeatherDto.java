package pl.coderslab.hikeappplanner.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherDto {

    private double temperature;

    private double windSpeed;

    private int humidity;

    private String sunrise;

    private String sunset;
}
