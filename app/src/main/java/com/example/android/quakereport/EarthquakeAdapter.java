package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(@NonNull Context context, int resource, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
        // *** notice we put 0, into resource id. so [resource input] will be ignored.
        // reason for this is,
        // We are going to use getView() method to generate views.
        // whereas if you give resourceId, super() method will make a views.
    }

    final static String SEPARATOR = "of ";

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentView = convertView;
        if (currentView == null) {
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.earthqauke_single_item_layout, parent, false);
        }

        TextView magView = currentView.findViewById(R.id.earthquake_mag);
        TextView distanceView = currentView.findViewById(R.id.earthquake_distance);
        TextView cityView = currentView.findViewById(R.id.earthquake_city);
        TextView dateView = currentView.findViewById(R.id.earthquake_date);
        TextView timeView = currentView.findViewById(R.id.earthquake_time);

        final Earthquake currentEarthquakeItem = getItem(position);

        int magnitudeIntValue = (int) Math.round(currentEarthquakeItem.getmMagnitude());
        int color;

        switch (magnitudeIntValue) {
            case 0:
                color = R.color.magnitude1;
                break;
            case 1:
                color = R.color.magnitude2;
                break;
            case 2:
                color = R.color.magnitude3;
                break;
            case 3:
                color = R.color.magnitude4;
                break;
            case 4:
                color = R.color.magnitude5;
                break;
            case 5:
                color = R.color.magnitude6;
                break;
            case 6:
                color = R.color.magnitude7;
                break;
            case 7:
                color = R.color.magnitude8;
                break;
            case 8:
                color = R.color.magnitude9;
                break;
            case 9:
                color = R.color.magnitude10plus;
                break;
            case 10:
                color = R.color.magnitude10plus;
                break;
            default:
                color = R.color.magnitude10plus;
                break;
        }

        magView.setText(getDecimalValueFromDouble(currentEarthquakeItem.getmMagnitude()));
        magView.getBackground().setTint(currentView.getResources().getColor(color));


        String location = currentEarthquakeItem.getmLocationDetails();
        String parts[];
        if (location.contains(SEPARATOR)) {
            parts = location.split(SEPARATOR, 2); // limit to separate only by 2 parts.
            distanceView.setText(parts[0].concat("of "));
            cityView.setText(parts[1]);
        } else {
            distanceView.setText(location);
        }

        String dateString = getCurrentTimeStamp(currentEarthquakeItem.getmTimeMillis());
        if (dateString != null) {
            String s1 = dateString.substring(0, 12);
            String s2 = dateString.substring(13, 21);
            dateView.setText(s1);
            timeView.setText(s2);
        } else {
            dateView.setText("date");
            timeView.setText("time");
        }

        currentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(currentEarthquakeItem.getmUrl()));
                getContext().startActivity(intent);
            }
        });

        return currentView;

    }

    public static String getCurrentTimeStamp(long timestamp) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("MMM dd, yyyy hh:mm a");//dd/MM/yyyy
        Date date = new Date(timestamp);
        String strDate = sdfDate.format(date);
        return strDate;
    }

    public static String getDecimalValueFromDouble(Double d) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(d);
    }

}