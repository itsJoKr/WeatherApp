package com.example.slaven.weatherapp.ui.main.adapter;

import com.example.slaven.weatherapp.data.forecast.List;

/**
 * Created by Slaven on 29.7.2015..
 * Package name: com.example.slaven.weatherapp.ui.main
 */
public interface AdapterActivityComm {

    // Interface to communicate from Adapter to activity

    public void onHolderClick(List detailData, String cityName);

}
