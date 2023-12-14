package pl.coderslab.hikeappplanner.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDayDto {

    @JsonProperty("maxtemp_c")
    private double maxtemp_c;
    @JsonProperty("mintemp_c")
    private double mintemp_c;
    @JsonProperty("maxwind_kph")
    private double maxwind_kph;
    @JsonProperty("condition")
    private WeatherConditionDto condition;

    public double getMaxtemp_c() {
        return maxtemp_c;
    }

    public double getMintemp_c() {
        return mintemp_c;
    }

    public double getMaxwind_kph() {
        return maxwind_kph;
    }

    public WeatherConditionDto getCondition() {
        return condition;
    }
}
