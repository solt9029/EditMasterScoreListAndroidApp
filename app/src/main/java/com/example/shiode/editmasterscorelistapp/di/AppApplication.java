package com.example.shiode.editmasterscorelistapp.di;

import android.app.Application;

public class AppApplication extends Application {
    private AppComponent component;
    private static AppApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        component = DaggerAppComponent.builder().build();
    }

    public AppComponent getComponent() {
        return component;
    }

    public static AppApplication getApplication() {
        return application;
    }
}
