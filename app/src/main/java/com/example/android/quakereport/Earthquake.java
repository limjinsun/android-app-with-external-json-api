package com.example.android.quakereport;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;


public class Earthquake {

    private double mMagnitude;

    private String mDistance;

    private String mLocation;

    private String mDate;

    private String mdate;

    public Earthquake() {
        // empty constructor
    }

    public Earthquake(double mMagnitude, String mDistance, String mLocation, String mDate, String mdate) {
        this.mMagnitude = mMagnitude;
        this.mDistance = mDistance;
        this.mLocation = mLocation;
        this.mDate = mDate;
        this.mdate = mdate;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmDistance() {
        return mDistance;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmDate() {
        return mDate;
    }

    public String getMdate() {
        return mdate;
    }

    public void setmMagnitude(double mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public void setmDistance(String mDistance) {
        this.mDistance = mDistance;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

}

