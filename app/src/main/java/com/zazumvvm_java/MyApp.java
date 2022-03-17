package com.zazumvvm_java;

import android.app.Application;

import com.zazumvvm_java.di.DaggerMyComponent;
import com.zazumvvm_java.di.DatabaseModule;
import com.zazumvvm_java.di.MyComponent;
import com.zazumvvm_java.di.SharedPrefModule;
import com.zazumvvm_java.di.UserModule;

public class MyApp extends Application {
    private static MyApp app;
    private MyComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appComponent = DaggerMyComponent.builder()
                .sharedPrefModule(new SharedPrefModule(this))
                .userModule(new UserModule("Hariiii"))
                .databaseModule(new DatabaseModule(this))
                .build();
    }

    public static MyApp app(){
        return app;
    }

    public MyComponent appComponent(){
        return appComponent;
    }
}
