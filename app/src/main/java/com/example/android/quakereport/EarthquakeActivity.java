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

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private EarthquakeAdapter earthquakeAdapter;
    private ListView earthquakeListView;

    private String REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_list_view);

        earthquakeAdapter = new EarthquakeAdapter(this, R.layout.earthquake_list_view, new ArrayList<Earthquake>());
        earthquakeListView = findViewById(R.id.earthquake_list_view);
        // findViewById() will look for view by ID.
        // inside of This activity's context view = R.layout.earthquake_list_view

//        List<Earthquake> earthquakes = new EarthquakeStringHandler().getList();

        new EarthquakeTasks().execute(REQUEST_URL);
    }


    private class EarthquakeTasks extends AsyncTask <String, Void, List<Earthquake>> {

        @Override
        protected List<Earthquake> doInBackground(String... strings) {
            // make http call
            URL url = HttpUtill.createUrl(strings[0]);
            String response = "";

            try {
                response = HttpUtill.makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return EarthquakeStringHandler.getList(response);
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            // update Adapter and UI
            super.onPostExecute(earthquakes);
            earthquakeAdapter = new EarthquakeAdapter(getApplicationContext(),
                    R.id.earthquake_single_item_layout, earthquakes);
            earthquakeListView.setAdapter(earthquakeAdapter);

        }

    }

}