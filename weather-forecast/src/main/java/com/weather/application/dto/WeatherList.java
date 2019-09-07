package com.weather.application.dto;

public class WeatherList {

	private String dt;
	private String dt_txt;
	private WeatherMain main;
	private Weather weather;
	private Cloud clouds;
	private Sys sys;
	private Wind wind;

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getDt_txt() {
		return dt_txt;
	}

	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}

	public WeatherMain getMain() {
		return main;
	}

	public void setMain(WeatherMain main) {
		this.main = main;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public Cloud getClouds() {
		return clouds;
	}

	public void setClouds(Cloud clouds) {
		this.clouds = clouds;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

}
