package com.example.kundan.demoubercabapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class APIListAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<JSONObject> dataList;

    public APIListAdapter(ArrayList<JSONObject> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    public int getCount() {
        return this.dataList.size();
    }

    public JSONObject getItem(int position) {
        return (JSONObject) this.dataList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = ((Activity) this.context).getLayoutInflater().inflate(R.layout.item_row, null, true);
        TextView tvVehicleName = (TextView) listViewItem.findViewById(R.id.tvVehicleName);
        TextView tvRideDuration = (TextView) listViewItem.findViewById(R.id.tvRideDuration);
        TextView tvRideDistance = (TextView) listViewItem.findViewById(R.id.tvRideDistance);
        TextView tvRideEstimate = (TextView) listViewItem.findViewById(R.id.tvRideEstimates);
        try {
                tvVehicleName.setText(getItem(position).getString("localized_display_name"));
           // tvRideDuration.setText(getItem(position).getString("duration"));
           tvRideDistance.setText("Ride Distance : " + getItem(position).getString("distance") + " mile");
            tvRideEstimate.setText("Ride Estimate : " + getItem(position).getString("estimate"));

            Double duration = getItem(position).getDouble("duration");
            Double localizedDuration = duration/60;
            tvRideDuration.setText("Ride Duration : "+ (localizedDuration).toString() + " min");



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listViewItem;
    }
}
