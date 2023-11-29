package pl.coderslab.hikeappplanner.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherWeatherForecast {

    @JsonProperty("forecast")
    private WeatherForecastDto forecast;

    public WeatherForecastDto getForecast() {
        return forecast;
    }
}
