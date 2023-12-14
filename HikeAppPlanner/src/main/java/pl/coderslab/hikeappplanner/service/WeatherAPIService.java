package pl.coderslab.hikeappplanner.service;

import org.springframework.stereotype.Service;
import pl.coderslab.hikeappplanner.model.WeatherDto;
import pl.coderslab.hikeappplanner.webclient.weather.WeatherClient;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeatherAPIService {

    private final WeatherClient weatherClient;

    public WeatherAPIService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    // dla płatnej usługi FutureApi
    public List<WeatherDto> getWeather(double lat, double lon, LocalDate date) {
        return weatherClient.getForecast(lat, lon, date);
    }

    // dla darmowej usługi ForecastApi
    public List<WeatherDto> getWeatherForecastApi(double lat, double lon, int days) {
        return weatherClient.getForecastApi(lat, lon, days);
    }
}
