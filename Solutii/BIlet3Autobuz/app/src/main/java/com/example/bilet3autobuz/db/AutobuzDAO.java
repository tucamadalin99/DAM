package com.example.bilet3autobuz.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bilet3autobuz.Autobuz;

import java.util.List;

@Dao
public interface AutobuzDAO {
    @Insert
    void insert(Autobuz autovehicul);

    @Query("SELECT * FROM autobuze")
    List<Autobuz>getAutobuzeDB();


}
