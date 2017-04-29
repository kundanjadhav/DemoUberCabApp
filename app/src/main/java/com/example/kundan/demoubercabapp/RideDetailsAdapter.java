package com.example.kundan.demoubercabapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kundan on 4/29/17.
 */

public class RideDetailsAdapter extends ArrayAdapter<RideDetails> {

        private final Context context;
        private final ArrayList<RideDetails> rideDetails;

        public RideDetailsAdapter(Context context, int resource, ArrayList<RideDetails> rideDetails)
        {
            super(context,resource );
            this.context = context;
            this.rideDetails= rideDetails;
        }
        @Override
        public int getCount() {
            return this.rideDetails.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LinearLayout layout = null;
            if (convertView==null)
            {
                Activity activity = (Activity) context;
                layout = (LinearLayout) activity.getLayoutInflater()
                        .inflate(R.layout.item_row,null);

            }
            else {
                layout = (LinearLayout) convertView;
            }

            RideDetails r = rideDetails.get(position);

            TextView textName = (TextView) layout.findViewById(R.id.tvVehicleName);
            TextView textDistance=(TextView) layout.findViewById(R.id.tvRideDistance);
            TextView textDuration = (TextView) layout.findViewById(R.id.tvRideDuration);
            TextView textEstimates = (TextView) layout.findViewById(R.id.tvRideEstimates);


            textName.setText(r.VehicleName);
            textDistance.setText(r.RideDistance);
            textDuration.setText(r.RideDuration);
            textEstimates.setText(r.RideEstimate);
            return layout;
        }
    }



