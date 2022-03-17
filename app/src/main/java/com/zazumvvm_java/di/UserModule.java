package com.zazumvvm_java.di;

import com.zazumvvm_java.model.Employee;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    private String uname;

    public UserModule(String uname){
        this.uname = uname;
    }

    @Singleton
    @Provides
    public Employee getEmployee(){
        return new Employee(uname);
    }
}
