package com.example.myapplication.di;

import android.app.Application;
import android.content.Context;

public class AppDeleagate extends Application {
    private static MainComponent mainComponent;
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.create();
        application=this;

    }

    public static MainComponent getMainComponent (){
        return mainComponent;
    }
    public static Application getApplication(){
        return application;
    }
    public static Context getAppContext(){
        return  getApplication().getApplicationContext();
    }
}
