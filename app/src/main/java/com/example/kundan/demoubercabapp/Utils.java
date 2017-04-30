package com.example.kundan.demoubercabapp;

import android.app.ProgressDialog;
import android.content.Context;

public class Utils {
    private boolean isShowingProgressBar = false;
    private ProgressDialog progressDialog;

    public boolean showProgress(String message, Context context) {
        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage(message);
        this.progressDialog.setCancelable(false);
        this.progressDialog.dismiss();
        this.progressDialog.show();
        this.isShowingProgressBar = true;
        return this.isShowingProgressBar;
    }

    public void dismissProgressBar(boolean isShowingProgressBar) {
        if (isShowingProgressBar) {
            this.progressDialog.dismiss();
        }
    }
}
