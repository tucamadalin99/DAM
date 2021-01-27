package com.example.bilet3autobuz.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bilet3autobuz.Autobuz;

@Database(entities = {Autobuz.class}, version = 1, exportSchema = false)
public abstract class AutobuzDB extends RoomDatabase {
    public abstract AutobuzDAO getAutobuzDAO();
}
