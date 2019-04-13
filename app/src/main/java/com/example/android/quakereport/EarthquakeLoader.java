package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String mUrl;

    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        // make a http connection and return List<Earthquake> object. This is Async Tasks.
        URL url = HttpUtill.createUrl(mUrl);
        String responds;
        try {
            responds = HttpUtill.makeHttpRequest(url);
        } catch (IOException e) {
            responds = null;
            e.printStackTrace();
        }
        List<Earthquake> earthquakes = EarthquakeStringHandler.getList(responds);

        return earthquakes;
    }

    @Override
    public void onStartLoading () {
        forceLoad();
    }

}
