package com.example.android.quakereport;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

public class Earthquake {

    private double mMagnitude;
    private String mLocationDetails;
    private long mTimeMillis;
    private String mUrl;

    public Earthquake(double mMagnitude, String mLocationDetails, long mTimeMillis, String mUrl) {
        this.mMagnitude = mMagnitude;
        this.mLocationDetails = mLocationDetails;
        this.mTimeMillis = mTimeMillis;
        this.mUrl = mUrl;
    }

    public Earthquake() {
        // empty constructor
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public void setmMagnitude(double mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public String getmLocationDetails() {
        return mLocationDetails;
    }

    public void setmLocationDetails(String mLocationDetails) {
        this.mLocationDetails = mLocationDetails;
    }

    public long getmTimeMillis() {
        return mTimeMillis;
    }

    public void setmTimeMillis(long mTimeMillis) {
        this.mTimeMillis = mTimeMillis;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }


}