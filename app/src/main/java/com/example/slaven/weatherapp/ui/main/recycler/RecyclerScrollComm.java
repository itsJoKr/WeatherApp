package com.example.slaven.weatherapp.ui.main.recycler;

/**
 * Created by JK on 29.8.2015..
 */
public interface RecyclerScrollComm {

    // This interface is for RecyclerView to send scroll state changes to activity, so we can check if toolbar is being collapsed/exp.

    public void onRecycleScrolled(int state);

}
