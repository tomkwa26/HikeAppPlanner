package pl.coderslab.hikeappplanner.webclient.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.hikeappplanner.dto.WeatherAstroDto;
import pl.coderslab.hikeappplanner.dto.WeatherDayDto;
import pl.coderslab.hikeappplanner.dto.WeatherForecastDayDto;
import pl.coderslab.hikeappplanner.dto.WeatherForecastDto;
import pl.coderslab.hikeappplanner.model.WeatherDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherClient {

    @Value("${weather.api.key}")
    private String weatherApiKey;

    private static final String WEATHER_URL = "https://api.weatherapi.com/v1";

    private RestTemplate restTemplate = new RestTemplate();

    public List<WeatherDto> getForecast(double lat, double lon, LocalDate date) {

        WeatherForecastDto weatherForecastDto = callGetMethod("/future.json?key={apiKey}&q={lat},{lon}&dt={date}&lang=pl",
                WeatherForecastDto.class,
                weatherApiKey, lat, lon, date);

        List<WeatherDto> weatherDtoList = new ArrayList<>();

        if (weatherForecastDto != null && weatherForecastDto.getForecastday() != null) {
            for (WeatherForecastDayDto forecastDayDto : weatherForecastDto.getForecastday()) {
                WeatherDayDto weatherDayDto = forecastDayDto.getDay();
                WeatherAstroDto weatherAstroDto = forecastDayDto.getAstro();

                if (weatherDayDto != null && weatherAstroDto != null) {
                    WeatherDto weatherDto = WeatherDto.builder()
                            .temperature(weatherDayDto.getMaxtemp_c())
                            .windSpeed(weatherDayDto.getMaxwind_kph())
                            .humidity(weatherDayDto.getAvghumidity())
                            .sunrise(weatherAstroDto.getSunrise())
                            .sunset(weatherAstroDto.getSunset())
                            .build();

                    weatherDtoList.add(weatherDto);
                }
            }
        }

        return weatherDtoList;


    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(WEATHER_URL + url, responseType, objects);
    }
}
