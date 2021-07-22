package com.example.bnetipartner.Repository;

import android.app.Application;

public class IntentApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Repository.initialize();
    }
}