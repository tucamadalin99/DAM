package com.example.bilet1.db;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bilet1.Cursa;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface CurseDAO {
    @Insert
    void insert(Cursa cursa);

    @Query("SELECT * FROM curse")
    List<Cursa> getCurse();

    @Query("SELECT cost FROM curse")
    float[] costuri();
}