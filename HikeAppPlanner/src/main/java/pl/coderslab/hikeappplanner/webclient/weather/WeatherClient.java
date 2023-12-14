package pl.coderslab.hikeappplanner.webclient.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.hikeappplanner.dto.*;
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

    // dla płatnej usługi FutureApi
    public List<WeatherDto> getForecast(double lat, double lon, LocalDate date) {

        // wywołanie metody z wykorzystaniem RestTemplate do pobrania prognozy pogody
        WeatherWeatherForecast weatherWeatherForecast = callGetMethod("/future.json?key={apiKey}&q={lat},{lon}&dt={date}&lang=pl",
                WeatherWeatherForecast.class,
                weatherApiKey, lat, lon, date);

        // utworzenie listy przechowującej obiekty WeatherDto
        List<WeatherDto> weatherDtoList = new ArrayList<>();

        // sprawdzenie czy odpowiedź z API oraz prognoza dnia są dostępne
        if (weatherWeatherForecast != null && weatherWeatherForecast.getForecast() != null
                && weatherWeatherForecast.getForecast().getForecastday() != null) {

            for (WeatherForecastDayDto forecastDayDto : weatherWeatherForecast.getForecast().getForecastday()) {
                WeatherDayDto weatherDayDto = forecastDayDto.getDay();
                WeatherAstroDto weatherAstroDto = forecastDayDto.getAstro();

                // budowanie obiektu na podstawie danych z zew.API
                if (weatherDayDto != null && weatherAstroDto != null) {
                    WeatherDto weatherDto = WeatherDto.builder()
                            .maxTemperature(weatherDayDto.getMaxtemp_c())
                            .minTemperature(weatherDayDto.getMintemp_c())
                            .windSpeed(weatherDayDto.getMaxwind_kph())
                            .conditionText(weatherDayDto.getCondition().getText())
                            .conditionIcon(weatherDayDto.getCondition().getIcon())
                            .sunrise(weatherAstroDto.getSunrise())
                            .sunset(weatherAstroDto.getSunset())
                            .build();

                    weatherDtoList.add(weatherDto);
                }
            }
        }

        return weatherDtoList;
    }

    // dla darmowej usługi ForecastApi
    public List<WeatherDto> getForecastApi(double lat, double lon, int days) {

        // wywołanie metody z wykorzystaniem RestTemplate do pobrania prognozy pogody
        WeatherWeatherForecast weatherWeatherForecastApi = callGetMethod("/forecast.json?key={apiKey}&q={lat},{lon}&days={days}&lang=pl",
                WeatherWeatherForecast.class,
                weatherApiKey, lat, lon, days);

        // utworzenie listy przechowującej obiekty WeatherDto
        List<WeatherDto> weatherDtoArrayList = new ArrayList<>();

        // sprawdzenie czy odpowiedź z API oraz prognoza dnia są dostępne
        if (weatherWeatherForecastApi != null && weatherWeatherForecastApi.getForecast() != null
                && weatherWeatherForecastApi.getForecast().getForecastday() != null) {

            for (WeatherForecastDayDto forecastDayDto : weatherWeatherForecastApi.getForecast().getForecastday()) {
                WeatherDayDto weatherDayDto = forecastDayDto.getDay();
                WeatherAstroDto weatherAstroDto = forecastDayDto.getAstro();

                // budowanie obiektu na podstawie danych z zew.API
                if (weatherDayDto != null && weatherAstroDto != null) {
                    WeatherDto weatherDto = WeatherDto.builder()
                            .maxTemperature(weatherDayDto.getMaxtemp_c())
                            .minTemperature(weatherDayDto.getMintemp_c())
                            .windSpeed(weatherDayDto.getMaxwind_kph())
                            .conditionText(weatherDayDto.getCondition().getText())
                            .conditionIcon(weatherDayDto.getCondition().getIcon())
                            .sunrise(weatherAstroDto.getSunrise())
                            .sunset(weatherAstroDto.getSunset())
                            .build();

                    weatherDtoArrayList.add(weatherDto);
                }
            }
        }

        return weatherDtoArrayList;
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(WEATHER_URL + url, responseType, objects);
    }
}
