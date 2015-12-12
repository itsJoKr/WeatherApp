package com.example.slaven.weatherapp.network;

import com.example.slaven.weatherapp.data.forecast.Forecast;
import com.example.slaven.weatherapp.data.weather.CurrentWeather;
import com.example.slaven.weatherapp.util.Condition;
import com.example.slaven.weatherapp.network.deserializers.ConditionDeserializer;
import com.example.slaven.weatherapp.network.deserializers.DateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Slaven on 25.7.2015..
 * Package name: com.example.slaven.weatherapp.network
 */
public class WeatherApiClient {

    private static ApiInterface sApiInterface;

    public static ApiInterface getApiClient(){
        if (sApiInterface == null){
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://api.openweathermap.org/data/2.5")
                    .setConverter(getConverter())
                    .build();

            sApiInterface = restAdapter.create(ApiInterface.class);
        }

        return sApiInterface;
    }

    private static Converter getConverter(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateDeserializer());
        builder.registerTypeAdapter(Condition.class, new ConditionDeserializer());

        Gson gson = builder.create();

        return new GsonConverter(gson);
    }

    public interface ApiInterface {

        @GET("/forecast?lat&lon&APPID=af29567e139fe06b6c2d050515cdff0c&units=metric")
        Observable<Forecast> getForecast(@Query("lat") double lat, @Query("lon") double lon);

        ///weather?lat=43.066434&lon=17.676700&APPID=af29567e139fe06b6c2d050515cdff0c&units=metric
        @GET("/weather?lat&lon&APPID=af29567e139fe06b6c2d050515cdff0c&units=metric")
        Observable<CurrentWeather> getCurrentWeather(@Query("lat") double lat, @Query("lon") double lon);

        @GET("/weather?lat&lon&APPID=af29567e139fe06b6c2d050515cdff0c&units=metric")
        void getCurrentWeatherCallback(@Query("lat") double lat, @Query("lon") double lon, Callback<CurrentWeather> callback);

    }
}
