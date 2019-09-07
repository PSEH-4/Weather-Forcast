package com.weather.application.dto;

import java.util.List;

public class WeatherResponse {

	private String cod;
	private String message;
	private String cnt;
	private List<WeatherList> list;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public List<WeatherList> getList() {
		return list;
	}

	public void setList(List<WeatherList> list) {
		this.list = list;
	}

}
