package com.example.myapplication.di;

import android.app.Application;
import android.content.Context;

public class AppDeleagate extends Application {

    private MainComponent mainComponent;
    private static Application application;

    public void setMainComponent(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.create();
        application=this;
    }

    public MainComponent getMainComponent (){
        return mainComponent;
    }
    public static Application getApplication(){
        return application;
    }
    public static Context getAppContext(){
        return  getApplication().getApplicationContext();
    }
}
