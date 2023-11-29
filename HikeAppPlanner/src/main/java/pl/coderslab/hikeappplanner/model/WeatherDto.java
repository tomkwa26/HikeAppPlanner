package pl.coderslab.hikeappplanner.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherDto {

    private double maxTemperature;

    private double minTemperature;

    private double windSpeed;

    private String sunrise;

    private String sunset;

    private String conditionText;

    private String conditionIcon;
}
