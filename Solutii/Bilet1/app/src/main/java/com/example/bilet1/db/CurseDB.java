package com.example.bilet1.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bilet1.Cursa;

@Database(entities = {Cursa.class}, version = 1, exportSchema = false)
public abstract class CurseDB extends RoomDatabase {
    public abstract CurseDAO getCurseDAO();
}
