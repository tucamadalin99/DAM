package com.example.bilet3.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bilet3.Autovehicul;

import java.util.List;

@Dao
public interface AutovehiculDAO {
    @Insert
    void insert(Autovehicul autovehicul);

    @Query("SELECT * FROM autovehicule")
    List<Autovehicul> vehiculeTotale();

    @Query("SELECT COUNT(*) FROM autovehicule WHERE aPlatit=:aPlatit")
    int countMasini(boolean aPlatit);

}
