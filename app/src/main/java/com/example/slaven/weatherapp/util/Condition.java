package com.example.slaven.weatherapp.util;

import android.util.Log;

import com.example.slaven.weatherapp.R;

/**
 * Created by Slaven on 26.7.2015..
 * Package name: com.example.slaven.weatherapp.util
 */
public enum Condition {
    CLEAR_SKY,
    FEW_CLOUDS,
    SCATTERED_CLOUDS,
    BROKEN_CLOUDS,
    SHOWER_RAIN,
    RAIN,
    THUNDERSTORM,
    SNOW,
    MIST,;

    public static Condition getConditionFromId(int id){
        if(id>=200 && id<300){
            return THUNDERSTORM;
        }
        else if(id>=300 && id<500) {
            return SHOWER_RAIN;
        }
        else if(id>=500 && id<505) {
            return RAIN;
        }
        else if(id==511) {
            return SNOW;
        }
        else if (id>511 && id<600){
            return SHOWER_RAIN;
        }
        else if (id >= 600 && id<700) {
            return SNOW;
        }
        else if (id >= 700 && id < 800) {
            return MIST;
        }
        else if (id==800) {return CLEAR_SKY;}
        else if (id == 801) {
            return FEW_CLOUDS;
        }
        else if (id == 802) {
            return SCATTERED_CLOUDS;
        }
        else if (id == 803) {
            return BROKEN_CLOUDS;
        }
        else if (id == 804) {
            return BROKEN_CLOUDS;
        }
        else {
            Log.e("USER", "Condition id parameter error! ID: " + id);
            return FEW_CLOUDS;
        }
    }

    public static int getMainImgId(Condition condition, boolean isDay){
        switch (condition){
            case CLEAR_SKY:
                if (isDay)
                    return R.drawable.d1b;
                else
                    return R.drawable.n1b;
            case FEW_CLOUDS:
                if (isDay)
                    return R.drawable.d2b;
                else
                    return R.drawable.n2b;
            case SCATTERED_CLOUDS:
                return R.drawable.dn3b;
            case BROKEN_CLOUDS:
                return R.drawable.dn4b;
            case SHOWER_RAIN:
                return R.drawable.dn5b;
            case RAIN:
                return R.drawable.dn6b;
            case THUNDERSTORM:
                return R.drawable.dn7b;
            case SNOW:
                return R.drawable.dn8b;
            case MIST:
                return R.drawable.dn9b;
            default:
                Log.e("USER", "Error getMainImgId parameters: " + condition.toString() + ", isDay: " + isDay);
                return 0;
        }
    }

    public static int getForecastImgId(Condition condition, boolean isDay){
        switch (condition){
            case CLEAR_SKY:
                if (isDay)
                    return R.drawable.d1s;
                else
                    return R.drawable.n1s;
            case FEW_CLOUDS:
                if (isDay)
                    return R.drawable.d2s;
                else
                    return R.drawable.n2s;
            case SCATTERED_CLOUDS:
                return R.drawable.d3s;
            case BROKEN_CLOUDS:
                return R.drawable.d4s;
            case SHOWER_RAIN:
                return R.drawable.d5s;
            case RAIN:
                return R.drawable.d6s;
            case THUNDERSTORM:
                return R.drawable.d7s;
            case SNOW:
                return R.drawable.d8s;
            case MIST:
                return R.drawable.d9s;
            default:
                Log.e("USER", "Error getForecastImgId parameters: " + condition.toString() + ", isDay: " + isDay);
                return 0;
        }
    }

    public static int getToolbarWeatherIcon(Condition condition, boolean isDay){
        switch (condition){
            case CLEAR_SKY:
                if (isDay)
                    return R.drawable.d1action;
                else
                    return R.drawable.n1action;
            case FEW_CLOUDS:
                if (isDay)
                    return R.drawable.d2action;
                else
                    return R.drawable.n2action;
            case SCATTERED_CLOUDS:
                return R.drawable.d3action;
            case BROKEN_CLOUDS:
                return R.drawable.d4action;
            case SHOWER_RAIN:
                return R.drawable.d5action;
            case RAIN:
                return R.drawable.d6action;
            case THUNDERSTORM:
                return R.drawable.d7action;
            case SNOW:
                return R.drawable.d8action;
            case MIST:
                return R.drawable.d9action;
            default:
                Log.e("USER", "Error getForecastImgId parameters: " + condition.toString() + ", isDay: " + isDay);
                return 0;
        }
    }
}
