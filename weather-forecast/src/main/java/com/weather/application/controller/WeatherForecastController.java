package com.weather.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.application.dto.Response;
import com.weather.application.service.WeatherService;

@RestController
@ControllerAdvice
public class WeatherForecastController {

	Logger logger = LoggerFactory.getLogger(WeatherForecastController.class);
	@Autowired
	private WeatherService weatherService;

	@GetMapping("/weather")
	public ResponseEntity<Response> getWeather(@RequestParam String cityId, @RequestParam String countryCode) {

		if (!StringUtils.isEmpty(cityId) && !StringUtils.isEmpty(countryCode)) {

			return new ResponseEntity<Response>(weatherService.getWeather(cityId, countryCode), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
		}
	}
}
