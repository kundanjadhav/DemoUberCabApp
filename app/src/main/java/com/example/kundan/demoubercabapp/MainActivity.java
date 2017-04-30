package com.example.kundan.demoubercabapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_ERR = "mainActivity";
    private APIListAdapter apiListAdapter;
    private String countryListUrl;
    private ListView lv_dataList;
    private View.OnClickListener mOnClickListener;
    private Snackbar snackbar;


    public class FetchCountryList extends AsyncTask<Void, String, String> {
        private boolean isShowingProgressBar;
        private StringBuilder stringBuilder;
        private Utils utils;

        protected String doInBackground(Void... params) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(MainActivity.this.countryListUrl).openConnection().getInputStream()));
                    this.stringBuilder = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        this.stringBuilder.append(line).append('\n');
                    }
                    System.out.print("Append output" + this.stringBuilder.toString());
                } catch (Exception e) {
                    Log.e(MainActivity.TAG_ERR, e.getLocalizedMessage());
                }


            return this.stringBuilder.toString();

        }

        protected void onPostExecute(String s) {
            System.out.print(" output" + this.stringBuilder.toString());

            try {
//                JSONArray jsonArray = new JSONArray(s);
//                final ArrayList<JSONObject> arrayList = new ArrayList();
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    arrayList.add(jsonArray.getJSONObject(i));
//                }

                JSONObject resObj = new JSONObject(s);
                JSONArray jArray = resObj.getJSONArray("prices");
                final ArrayList<JSONObject> arrayList = new ArrayList();

                for (int i = 0; i < jArray.length(); i++) {
                    arrayList.add(jArray.getJSONObject(i));
                }
                MainActivity.this.apiListAdapter = new APIListAdapter(arrayList, MainActivity.this);
                MainActivity.this.lv_dataList.setAdapter(MainActivity.this.apiListAdapter);
                MainActivity.this.apiListAdapter.notifyDataSetChanged();
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        FetchCountryList.this.utils.dismissProgressBar(FetchCountryList.this.isShowingProgressBar);
                    }
                });

            } catch (Exception e) {
                Log.e(MainActivity.TAG_ERR, e.getLocalizedMessage());
            }
            super.onPostExecute(s);
        }

        protected void onPreExecute() {
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    FetchCountryList.this.utils = new Utils();
                    FetchCountryList.this.isShowingProgressBar = FetchCountryList.this.utils.showProgress("Loading", MainActivity.this);
                }
            });
            super.onPreExecute();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);

        //// added location coordinates for start address : "Swargate,Pune" , End address : "Katraj,Pune"
        this.countryListUrl = "https://api.uber.com/v1/estimates/price?start_latitude=18.5018322&start_longitude=73.8635912&end_latitude=18.4575324&end_longitude=73.8677464&server_token=2RJx619BeJ1gj1HlHjiKCt-ntL5ftyk6qh5C7gUB";
        this.lv_dataList = (ListView) findViewById(R.id.lv_dataList);
        if (new UIHelper().isNetworkAvailable(MainActivity.this.getApplicationContext())) {
            new FetchCountryList().execute(new Void[0]);
        } else {
            snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Network not available", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (new UIHelper().isNetworkAvailable(MainActivity.this.getApplicationContext())) {
                                snackbar.dismiss();
                            } else {
                                snackbar.show();
                            }

                        }
                    });
            snackbar.setActionTextColor(Color.RED);
            snackbar.show();
        }

    }


}
