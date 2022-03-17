package com.zazumvvm_java.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.zazumvvm_java.room.dao.NoteDao;
import com.zazumvvm_java.room.entities.Note;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteDatabaseDragger extends RoomDatabase {
    public abstract NoteDao noteDao();
}
