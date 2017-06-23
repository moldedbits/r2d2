package com.example.rahul.keystore;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Rahul
 * on 22-06-2017.
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        // fresh start
        LocalStorage.getInstance().clear();
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}