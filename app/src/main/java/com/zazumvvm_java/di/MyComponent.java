package com.zazumvvm_java.di;

import android.app.Application;

import com.zazumvvm_java.LoginDIActivity;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {SharedPrefModule.class,UserModule.class,DatabaseModule.class})
public interface MyComponent {
    void inject(LoginDIActivity activity);
    void inject(Application activity);
}
