package com.example.marioclassen.myapplication;

import android.app.Application;
import android.net.http.HttpResponseCache;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.File;

/**
 * Created by marioclassen on 5/10/16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

    }
}
