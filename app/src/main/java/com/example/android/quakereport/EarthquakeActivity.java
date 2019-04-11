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
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {
    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_list_view);

//        List<Earthquake> earthquakes = new JsonToListObject().getList();

        String str = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
        new EarthquakeTasks().execute(str);

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

            return JsonToListObject.getList(response);
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            // update Adapter and UI
            super.onPostExecute(earthquakes);

            EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(getApplicationContext(),
                    R.id.earthquake_single_item_layout, earthquakes);

            ListView earthquakeListView = (ListView) findViewById(R.id.earthquake_list_view);
            // findViewById() will look for view by ID.
            // inside of This activity's context view = R.layout.earthquake_list_view
            earthquakeListView.setAdapter(earthquakeAdapter);

        }
    }
}
