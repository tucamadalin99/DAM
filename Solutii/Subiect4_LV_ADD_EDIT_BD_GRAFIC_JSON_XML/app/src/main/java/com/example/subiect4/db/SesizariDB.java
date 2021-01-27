package com.example.subiect4.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.subiect4.DateConverter;
import com.example.subiect4.Sesizare;

@Database(entities = {Sesizare.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class SesizariDB extends RoomDatabase {
    public abstract SesizariDAO getSesizariDAO();
}
