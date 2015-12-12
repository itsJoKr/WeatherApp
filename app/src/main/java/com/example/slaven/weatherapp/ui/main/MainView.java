package com.example.slaven.weatherapp.ui.main;

import com.example.slaven.weatherapp.data.forecast.Forecast;
import com.example.slaven.weatherapp.data.weather.CurrentWeather;

/**
 * Created by Slaven on 25.7.2015..
 * Package name: com.example.slaven.weatherapp.ui.main
 */
public interface MainView {

    public void showCurrentWeather(CurrentWeather currentWeather);

    public void showForecast(Forecast forecast);

    public void showError(String error);

}
