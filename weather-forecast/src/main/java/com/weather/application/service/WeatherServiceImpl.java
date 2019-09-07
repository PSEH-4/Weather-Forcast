package com.weather.application.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.weather.application.controller.WeatherForecastController;
import com.weather.application.dto.Response;
import com.weather.application.dto.Weather;
import com.weather.application.dto.WeatherList;
import com.weather.application.dto.WeatherMain;
import com.weather.application.dto.WeatherResponse;
import com.weather.application.exception.WeatherException;

public class WeatherServiceImpl implements WeatherService {

	Logger logger = LoggerFactory.getLogger(WeatherForecastController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Response getWeather(String cityId, String countryCode) {

		ResponseEntity<WeatherResponse> response = null;
		try {
			response = restTemplate.getForEntity(
					"api.openweathermap.org/data/2.5/forecast?q=" + cityId + "," + countryCode, WeatherResponse.class);

		} catch (RestClientException e) {
			logger.error("Exception occured while calling Weather forecast api");
			throw new WeatherException("00", e.getLocalizedMessage());
		}
		return getResponse(response.getBody());
	}

	private Response getResponse(WeatherResponse weatherResponse) {
		Response response = null;
		double tempMin = 0.0d;
		double tempMax = 0.0d;
		boolean flag = true;
		LocalDateTime endDate = null;
		if (Objects.nonNull(weatherResponse) && weatherResponse.getCod() == "200") {
			response = new Response();
			List<WeatherList> list = weatherResponse.getList();
			for (WeatherList weatherList : list) {
				LocalDateTime date = convertStringToDate(weatherList.getDt_txt());
				if (null == endDate || date.isBefore(endDate)) {
					WeatherMain weatherMain = weatherList.getMain();
					Weather weather = weatherList.getWeather();
					if (flag) {
						tempMin = weatherMain.getTemp_min();
						tempMax = weatherMain.getTemp_max();
						endDate = date.plusDays(3);
						flag = false;
					}
					if (Objects.nonNull(weatherMain)) {
						if (weatherMain.getTemp_min() < tempMin) {
							tempMin = weatherMain.getTemp_min();
						} else if (weatherMain.getTemp_max() > tempMax) {
							tempMax = weatherMain.getTemp_max();
						}
					}
					if (Objects.nonNull(weather) && "Rain".equalsIgnoreCase(weather.getMain())) {
						response.setMessage("Carry umbrella");
					}
				}

			}
			if (tempMax > 40.0 && Objects.isNull(response.getMessage())) {
				response.setMessage("Use sunscreen lotion");
			}
			response.setTempMax(tempMax);
			response.setTempMin(tempMin);

		}
		return response;
	}

	private LocalDateTime convertStringToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		return dateTime;

	}

}
