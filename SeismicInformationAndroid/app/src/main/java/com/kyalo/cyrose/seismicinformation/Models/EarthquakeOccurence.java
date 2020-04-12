package com.kyalo.cyrose.seismicinformation.Models;


import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;

/*
 * Name: Cyrose Kyalo
 * Matric Number: S1803439
 */

public class EarthquakeOccurence {

    private String dateTime;
    private Date date;
    private String location;
    private double longitude;
    private double latitude;
    private int depth;
    private double magnitude;
    private String color;
    private LatLng latLng;

    public LatLng getLatLng() {
        return latLng;
    }

    public Date getDate() {
        return date;
    }

    public  EarthquakeOccurence() {


    }

    public String getColor() {
        return color;
    }

    public EarthquakeOccurence(@NonNull String dateTime, String location, double longitude, double latitude, int depth, double magnitude) {
        this.dateTime = dateTime;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.depth = depth;
        this.magnitude = magnitude;

        this.latLng = new LatLng(latitude, longitude);

        // Parse the date
        try {
            this.date = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss", Locale.ENGLISH).parse(dateTime);
        } catch (ParseException e) {
            Log.d("Parse Exception",e.getLocalizedMessage());
        }

        if (magnitude > 7) this.color = "#ff3d4a";
        else if (magnitude > 6) this.color = "#ff7936";
        else this.color = "#ffaf2d";
    }



    public String getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getDepth() {
        return depth;
    }

    public double getMagnitude() {
        return magnitude;
    }
}