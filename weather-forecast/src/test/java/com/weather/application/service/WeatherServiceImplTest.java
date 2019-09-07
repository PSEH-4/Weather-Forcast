package com.weather.application.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.weather.application.dto.Weather;
import com.weather.application.dto.WeatherList;
import com.weather.application.dto.WeatherMain;
import com.weather.application.dto.WeatherResponse;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {

	@InjectMocks
	private WeatherServiceImpl weatherServiceImpl;
	@Mock
	private RestTemplate restTemplate;

	@Test
	public void getWeatherTest() {
		String city = "city";
		String country = "country";
		ResponseEntity<WeatherResponse> response = null;
		WeatherResponse weatherResponse = new WeatherResponse();
		weatherResponse.setCnt("200");
		List<WeatherList> list = new ArrayList<>();
		WeatherList weatherList = new WeatherList();
		weatherList.setDt_txt("2017-02-16 12:00:00");
		WeatherMain main = new WeatherMain();
		main.setTemp_max(10);
		main.setTemp_min(6);
		weatherList.setMain(main);
		Weather weather = new Weather();
		weather.setMain("Rain");
		weatherList.setWeather(weather);
		list.add(weatherList);
		weatherResponse.setList(list);
		response = new ResponseEntity<WeatherResponse>(weatherResponse, HttpStatus.OK);
		Mockito.when(restTemplate.getForEntity("api.openweathermap.org/data/2.5/forecast?q=" + city + "," + country,
				WeatherResponse.class)).thenReturn(response);

		assertNotNull(weatherServiceImpl.getWeather("city", "country"));
	}
}
