package com.example.slaven.weatherapp.util;

import android.util.Log;

/**
 * Created by Slaven on 29.7.2015..
 * Package name: com.example.slaven.weatherapp.util
 */
public class WindDirection {

    public static String getWindDirection (double degree){
        if (degree>=0 && degree <33.75){
            return "N";
        }
        else if (degree>=33.75 && degree<56.25){
            return "NE";
        }
        else if (degree>=56.25 && degree<123.75){
            return "E";
        }
        else if (degree>=123.75 && degree<146.25){
            return "SE";
        }
        else if (degree>=146.25 && degree<213.75){
            return "S";
        }
        else if (degree>=213.75 && degree<236.25){
            return "SW";
        }
        else if(degree>=236.25 && degree<303.75){
            return "W";
        }
        else if (degree>=303.75 && degree<326.25){
            return "NW";
        }
        else if (degree>=326.25 && degree<=360){
            return "N";
        }
        else {
            Log.e("USER", "error returning Wind Direction, degree: " + degree);
            return "0";
        }
    }
}
