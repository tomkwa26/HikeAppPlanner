package pl.coderslab.hikeappplanner.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherConditionDto {

    @JsonProperty("text")
    private String text;
    @JsonProperty("icon")
    private String icon;

    public String getText() {
        return text;
    }

    public String getIcon() {
        return icon;
    }
}
