package com.example.slaven.weatherapp.events;

import android.location.Location;

/**
 * Created by Slaven on 31.7.2015..
 * Package name: com.example.slaven.weatherapp.comm
 */
public class LastLocationEvent {

    // This event is used by LocationAPI when it gets current location

    private Location location;

    public LastLocationEvent(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
