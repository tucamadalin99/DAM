package com.example.subiect4.db;

import android.view.ViewDebug;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.subiect4.Sesizare;

import java.util.List;

@Dao
public interface SesizariDAO {
    @Insert
    void insert(Sesizare sesizare);

    @Query("SELECT * FROM sesizari")
    List<Sesizare> getAllDB();
}
