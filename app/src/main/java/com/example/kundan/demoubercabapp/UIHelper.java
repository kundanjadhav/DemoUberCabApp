package com.example.kundan.demoubercabapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class UIHelper {
    public boolean isNetworkAvailable(Context context) {
        NetworkInfo netInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
