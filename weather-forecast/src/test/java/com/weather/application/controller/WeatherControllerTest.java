package com.weather.application.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.weather.application.dto.Response;
import com.weather.application.service.WeatherService;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

	@Mock
	private WeatherService weatherService;

	@InjectMocks
	private WeatherForecastController weatherController;

	@Test
	public void testGetWeatherWhenReturnsResponse() {
		Response res = new Response();
		res.setMessage("test");
		res.setTempMax(4);
		res.setTempMin(2);

		Mockito.when(weatherService.getWeather("city", "country")).thenReturn(res);
		assertTrue(weatherController.getWeather("city", "country").getBody().getMessage().equalsIgnoreCase("test"));
	}

	@Test
	public void testGetWeatherWhenCountryIsNull() {
		assertTrue(weatherController.getWeather("city", null).getStatusCode() == HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testGetWeatherWhenCityIsNull() {
		assertTrue(weatherController.getWeather(null, "country").getStatusCode() == HttpStatus.BAD_REQUEST);
	}
}
