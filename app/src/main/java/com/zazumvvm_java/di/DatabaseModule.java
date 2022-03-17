package com.zazumvvm_java.di;

import android.content.Context;

import androidx.room.Room;

import com.zazumvvm_java.room.NoteDatabaseDragger;
import com.zazumvvm_java.room.dao.NoteDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    private Context context;

    public DatabaseModule(Context context) {
        this.context = context;
    }
    /*

    @Singleton
    @Provides
    public Context provideContext(){
        return context;
    }*/

    @Singleton
    @Provides
    public NoteDatabaseDragger provideMyDatabase(Context context){
        return Room.databaseBuilder(context, NoteDatabaseDragger.class, "my-db").build();
    }

    @Singleton
    @Provides
    public NoteDao provideUserDao(NoteDatabaseDragger myDatabase){
        return myDatabase.noteDao();
    }
}
