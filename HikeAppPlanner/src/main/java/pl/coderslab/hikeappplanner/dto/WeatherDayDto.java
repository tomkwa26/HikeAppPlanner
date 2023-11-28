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
    @JsonProperty("avghumidity")
    private int avghumidity;
    @JsonProperty("condition")
    private WeatherConditionDto condition;

    public double getMaxtemp_c() {
        return maxtemp_c;
    }

    public void setMaxtemp_c(double maxtemp_c) {
        this.maxtemp_c = maxtemp_c;
    }

    public double getMintemp_c() {
        return mintemp_c;
    }

    public void setMintemp_c(double mintemp_c) {
        this.mintemp_c = mintemp_c;
    }

    public double getMaxwind_kph() {
        return maxwind_kph;
    }

    public void setMaxwind_kph(double maxwind_kph) {
        this.maxwind_kph = maxwind_kph;
    }

    public int getAvghumidity() {
        return avghumidity;
    }

    public void setAvghumidity(int avghumidity) {
        this.avghumidity = avghumidity;
    }

    public WeatherConditionDto getCondition() {
        return condition;
    }

    public void setCondition(WeatherConditionDto condition) {
        this.condition = condition;
    }
}
