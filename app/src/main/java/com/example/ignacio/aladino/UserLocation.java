package com.example.ignacio.aladino;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.google.firebase.firestore.GeoPoint;


public class UserLocation implements LocationListener {

    private LocationManager locationManager;
    private Context context;
    private static Double latitud;
    private static Double longitud;
    private Location location;

    public UserLocation(Context context){
        this.context = context;
        setUpLocation();
    }

    private void setUpLocation() {
        locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (isNetworkEnabled || isGPSEnabled) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        else if (!isNetworkEnabled && isGPSEnabled){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        latitud = location.getLatitude();
        longitud = location.getLongitude();

    }

    public static Double getLatitud(){
        return latitud;
    }

    public static Double getLongitud() {
        return longitud;
    }

    @Override
    public void onLocationChanged(Location location) {
        latitud = location.getLatitude();
        longitud = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
