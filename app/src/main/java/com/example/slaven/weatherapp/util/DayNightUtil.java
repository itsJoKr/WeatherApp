package com.example.slaven.weatherapp.util;

import android.util.Log;

import com.example.slaven.weatherapp.data.forecast.Forecast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Slaven on 26.7.2015..
 * Package name: com.example.slaven.weatherapp.util
 */
public class DayNightUtil {

    // Icon id from server will be in format: 40d, 60n d - Day, n - Night
    public static boolean isDayIcon(String iconId) {
        if ('d' == iconId.charAt(2)){
            return true;
        }
        else if ('n' == iconId.charAt(2)){
            return false;
        }
        else {
            Log.e("USER", "Error iconId d/n char: " + iconId.charAt(2));
            return true;
        }
    }

    // This counts how much days are in forecast data
    public static int checkDaysInForecast(Forecast forecast){

        List<com.example.slaven.weatherapp.data.forecast.List> forecastList = forecast.getList();
        List<Integer> daysCounted = new ArrayList<>();

        for (int i = 0; i < forecastList.size(); i++) {
            Date date = forecastList.get(i).getDt_txt();
            if (!daysCounted.contains(date.getDay())){
                daysCounted.add(date.getDay());
            }
        }

        return daysCounted.size();
    }

    // To show 08:00 instead 8:00
    public static String getHourString(int hrs){
        if (hrs < 10){
            return "0"+hrs;
        }
        else {
            return hrs+"";
        }
    }


}
