package com.example.slaven.weatherapp.ui.main.adapter;

import com.example.slaven.weatherapp.data.forecast.Forecast;
import com.example.slaven.weatherapp.data.forecast.List;
import com.example.slaven.weatherapp.ui.main.ForecastAdapter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Slaven on 27.7.2015..
 * Package name: com.example.slaven.weatherapp.util
 */
public class AdapterPresenter {

    /** This class will format download data for Forecast Adapter. Constructor get download data and generate
     *  view holders representation, which will be displayed.
     * **/

    private static final int TYPE_DAY_HOLDER = ForecastAdapter.DAY_NAME_HOLDER;
    private static final int TYPE_WEATHER_HOLDER = ForecastAdapter.WEATHER_HOLDER;

    Forecast forecastData;
    java.util.List<TempDataHolder> adapterData;
    java.util.List<Integer> daysShown;

    private String cityName;

    public AdapterPresenter(Forecast forecastData) {
        this.forecastData = forecastData;
        cityName = forecastData.getCity().getName();
        adapterData = new ArrayList<>();
        daysShown = new ArrayList<>();
        makeAdapterData();
    }

    public String getCityName() {
        return cityName;
    }


    private void makeAdapterData(){
        java.util.List<List> forecastList = forecastData.getList();

        for (int i =0; i<forecastList.size(); i++){
            if (!daysShown.contains(forecastList.get(i).getDt_txt().getDay())){
                adapterData.add(new TempDataHolder(TYPE_DAY_HOLDER, forecastList.get(i).getDt_txt(), null));
                daysShown.add(forecastList.get(i).getDt_txt().getDay());
                i = i-1;
            }
            else {
                adapterData.add(new TempDataHolder(TYPE_WEATHER_HOLDER, null, forecastList.get(i)));
            }
        }
    }

    public java.util.List<TempDataHolder> getAdapterData() {
        return adapterData;
    }


    // This class represent one row in Recycler View, it can be day name type (eg. Tue, Sun) or forecast type.
    public class TempDataHolder {

        private int typeHolder;
        private Date day;
        private String dayName;
        private List weatherData;

        public TempDataHolder(int typeHolder, Date day, List weatherData) {
            this.typeHolder = typeHolder;
            this.day = day;
            this.weatherData = weatherData;
        }


        public int getTypeHolder() {
            return typeHolder;
        }

        public Date getDay() {
            return day;
        }

        public String getDayName() {
            String dateString = day.toString();

            return String.valueOf(dateString.charAt(0)) + dateString.charAt(1) + dateString.charAt(2);
        }

        public List getWeatherData() {
            return weatherData;
        }
    }
}
