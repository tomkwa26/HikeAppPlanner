package pl.coderslab.hikeappplanner.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastDto {

    @JsonProperty("forecastday")
    private List<WeatherForecastDayDto> forecastday;

    public List<WeatherForecastDayDto> getForecastday() {
        return forecastday;
    }
}
