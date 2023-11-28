package pl.coderslab.hikeappplanner.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastDayDto {

    @JsonProperty("day")
    private WeatherDayDto day;
    @JsonProperty("astro")
    private WeatherAstroDto astro;
    public WeatherDayDto getDay() {
        return day;
    }
    public void setDay(WeatherDayDto day) {
        this.day = day;
    }
    public WeatherAstroDto getAstro() {
        return astro;
    }
    public void setAstro(WeatherAstroDto astro) {
        this.astro = astro;
    }
}
