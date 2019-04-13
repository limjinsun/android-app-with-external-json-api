/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private EarthquakeAdapter earthquakeAdapter;
    private ListView earthquakeListView;
    private TextView emptyStateTextView;

    private static final int EARTHQUAKE_LOADER_ID = 1;

    private String REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_list_view);

        earthquakeAdapter = new EarthquakeAdapter(this, R.layout.earthquake_list_view, new ArrayList<Earthquake>());
        earthquakeListView = findViewById(R.id.earthquake_list_view);
        emptyStateTextView = findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(emptyStateTextView);


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Initialise new Loader
            android.app.LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);

        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message
            emptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    @NonNull
    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, @Nullable Bundle bundle) {
        // create Loader.
        return new EarthquakeLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        emptyStateTextView.setText(R.string.no_earthquakes);

        // Before update UI, clear the Adapter.
        earthquakeAdapter.clear();


        // if you get new data, add it to Adapter.
        if(earthquakes != null && !earthquakes.isEmpty()){
            earthquakeAdapter.addAll(earthquakes);
        }
        // update UI
        earthquakeListView.setAdapter(earthquakeAdapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Earthquake>> loader) {
        // if Loader reset, clear the updater for making new view.
        earthquakeAdapter.clear();
    }

}