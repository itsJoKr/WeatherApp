package com.example.slaven.weatherapp.network;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.example.slaven.weatherapp.events.LastLocationEvent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import de.greenrobot.event.EventBus;

/**
 * Created by Slaven on 31.7.2015..
 * Package name: com.example.slaven.weatherapp.network
 */
public class LocationApi implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    private Location lastLocation;

    public void getLastKnownLocation(Context context) {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("USER", "onConnected called");
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null) {
            EventBus.getDefault().post(new LastLocationEvent(lastLocation));
        } else {
            Log.e("USER", "lastLocation is null");
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        //do nothing...
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("USER", "onConnectionFailed LocationApi " + connectionResult.toString());
    }
}
