package com.weather.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class WeatherExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { WeatherException.class, WeatherException.class })
	public ResponseEntity<Object> handleWeatherException(WeatherException ex, WebRequest request) {

		return new ResponseEntity<Object>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
