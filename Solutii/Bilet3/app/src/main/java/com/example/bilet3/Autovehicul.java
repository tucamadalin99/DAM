package com.example.bilet3;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "autovehicule")
public class Autovehicul implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    private int numarAuto;
    private String dataInregistare;
    private int idLocParcare;
    public boolean aPlatit;

    public Autovehicul(int numarAuto, String dataInregistare, int idLocParcare, boolean aPlatit) {
        this.numarAuto = numarAuto;
        this.dataInregistare = dataInregistare;
        this.idLocParcare = idLocParcare;
        this.aPlatit = aPlatit;
    }

    public int getNumarAuto() {
        return numarAuto;
    }

    public void setNumarAuto(int numarAuto) {
        this.numarAuto = numarAuto;
    }

    public String getDataInregistare() {
        return dataInregistare;
    }

    public void setDataInregistare(String dataInregistare) {
        this.dataInregistare = dataInregistare;
    }

    public int getIdLocParcare() {
        return idLocParcare;
    }

    public void setIdLocParcare(int idLocParcare) {
        this.idLocParcare = idLocParcare;
    }

    public boolean isaPlatit() {
        return aPlatit;
    }

    public void setaPlatit(boolean aPlatit) {
        this.aPlatit = aPlatit;
    }

    @Override
    public String toString() {
        return  "numarAuto=" + numarAuto +
                ", dataInregistare=" + dataInregistare +
                ", idLocParcare=" + idLocParcare +
                ", aPlatit=" + aPlatit;
    }
}
