package com.example.bilet3.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bilet3.Autovehicul;

@Database(entities = {Autovehicul.class}, version = 2, exportSchema = false)
public abstract class AutovehiculBD extends RoomDatabase {
    public abstract AutovehiculDAO getAutovehiculDAO();
}
