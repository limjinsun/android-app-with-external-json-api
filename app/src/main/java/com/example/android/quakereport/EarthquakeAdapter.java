package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(@NonNull Context context, int resource, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
        // *** notice we put 0, into resource id. so [resource input] will be ignored.
        // reason for this is,
        // We are going to use getView() method to generate views.
        // whereas if you give resourceId, super() method will make a views.
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentView = convertView;
        if(currentView == null){
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.earthqauke_single_item_layout, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

//        TextView magTextView = (TextView) currentView.findViewById(R.id.earthquake_mag);
//        magTextView.setText(Double.toString((currentEarthquake.getmMagnitude())));
//        TextView locaTextView = (TextView) currentView.findViewById(R.id.earthquake_loca1);
//        locaTextView.setText(currentEarthquake.getmLocation());
//        TextView dateTextView = (TextView) currentView.findViewById(R.id.earthquake_date);
//        dateTextView.setText(Long.toString(currentEarthquake.getmDate()));

        return currentView;

    }

}