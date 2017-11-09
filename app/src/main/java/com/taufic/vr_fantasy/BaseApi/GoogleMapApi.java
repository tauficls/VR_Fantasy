package com.taufic.vr_fantasy.BaseApi;

/**
 * Created by taufic on 11/9/2017.
 */

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Google API helper class. This is used to do the login/auth Google
 */

public class GoogleMapApi {

    public interface GoogleLocationCallBack {
        void OnSuccess(Location location);
    }

    private static GoogleApiClient googleApiClient;

    private static GoogleLocationCallBack googleLocationCallBack;

    private static Location lastLocation;

    /**
     * Get Instance of google Auth client
     */
    public static GoogleApiClient getGoogleApiClient(Activity activity) {
        if (googleApiClient == null) {
            initGoogleLocation(activity);
        }
        return googleApiClient;
    }

    public static void initGoogleLocation(final Activity activity) {

        // Toast an error when fail to connect.
        GoogleApiClient.OnConnectionFailedListener onFailure = new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(activity, connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                Log.d("GOOGLE API ", "Connected");
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                } else {
                    lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    googleLocationCallBack.OnSuccess(lastLocation);
                }
            }

            @Override
            public void onConnectionSuspended(int i) {

            }
        };

        googleApiClient = new GoogleApiClient.Builder(activity)
            .addConnectionCallbacks(connectionCallbacks)
            .addOnConnectionFailedListener(onFailure)
            .addApi(LocationServices.API)
            .build();

        googleApiClient.connect();
    }

    public static void onStart() {
        if (googleApiClient != null && !googleApiClient.isConnected()) {
            googleApiClient.connect();
        }
    }

    public static void onStop() {
        if( googleApiClient != null && googleApiClient.isConnected() ) {
            googleApiClient.disconnect();
        }
    }

    public static void myLocation(Activity activity, GoogleLocationCallBack callBack) {
        googleLocationCallBack = callBack;
        googleApiClient = getGoogleApiClient(activity);
    }
}
