package com.example.slaven.weatherapp.ui.main;

import android.location.Location;
import android.util.Log;
import android.util.Pair;

import com.example.slaven.weatherapp.events.LastLocationEvent;
import com.example.slaven.weatherapp.data.forecast.Forecast;
import com.example.slaven.weatherapp.data.weather.CurrentWeather;
import com.example.slaven.weatherapp.network.WeatherApiClient;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Slaven on 26.7.2015..
 * Package name: com.example.slaven.weatherapp.ui.main
 */
public class MainPresenterImpl implements MainPresenter {

    MainView mainView;
    Location location;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        EventBus.getDefault().register(this);
    }

    // Presenter now downloads data when it gets location
//    @Override
//    public void downloadData() {
//
//    }

    // When LocationApi gets device location, this event will be called. So we download data here as soon as we get location.
    // API is set that way, that this download is only one required for whole app.
    @Subscribe
    public void onEvent(final LastLocationEvent event){
        location = event.getLocation();
        Log.d("USER", "onEvent LastLocationEvent - presenter");

        WeatherApiClient.getApiClient().getCurrentWeatherCallback(location.getLatitude(), location.getLongitude(),
                new Callback<CurrentWeather>() {
                    @Override
                    public void success(CurrentWeather currentWeather, Response response) {
                        Log.d("USER", "Callback success");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("USER", "Callback failure: " + error.toString());
                    }
                });


        /** We have two network calls for CurrentWeather and for weekly Forecast. Standard async tasks have thread pool limited to 1:
         * to avoid common application errors caused by parallel execution. So it would require some extra work to set
         * up parallel execution.
         * Retrofit async download would be "callback hell", so RxJava is used.
         **/
        Observable.combineLatest(WeatherApiClient.getApiClient().getCurrentWeather(location.getLatitude(), location.getLongitude()), WeatherApiClient.getApiClient().getForecast(location.getLatitude(), location.getLongitude()),
                (currentWeather, forecast) -> new Pair<CurrentWeather, Forecast>(currentWeather, forecast))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> Observable.empty())
                .subscribe(new Observer<Pair<CurrentWeather, Forecast>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("USER", "obs - onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.showError("No internet connection!");
                    }

                    @Override
                    public void onNext(Pair<CurrentWeather, Forecast> currentWeatherForecastPair) {
                        if (currentWeatherForecastPair != null) {
                            mainView.showCurrentWeather(currentWeatherForecastPair.first);
                            mainView.showForecast(currentWeatherForecastPair.second);
                        }
                    }
                });
    }

//currentWeatherForecastPair -> {
//
//
//        if (currentWeatherForecastPair != null) {
//        mainView.showCurrentWeather(currentWeatherForecastPair.first);
//        mainView.showForecast(currentWeatherForecastPair.second);
//        }

}
