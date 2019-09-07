package com.weather.application.service;

import com.weather.application.dto.Response;

public interface WeatherService {

	Response getWeather(String cityId, String countryCode);
}
