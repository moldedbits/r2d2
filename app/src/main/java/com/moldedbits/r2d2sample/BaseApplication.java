package com.moldedbits.r2d2sample;

import android.app.Application;

import com.moldedbits.rahul.keystore.BuildConfig;

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

        // fresh start
        LocalStorage.getInstance().clear();
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}