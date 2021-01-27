package com.example.bilet3autobuz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "autobuze")
public class Autobuz implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String nrInmatriculare;
    private int linie;
    private String sofer;
    private int nrLocuri;

    public Autobuz(String nrInmatriculare, int linie, String sofer, int nrLocuri) {
        this.nrInmatriculare = nrInmatriculare;
        this.linie = linie;
        this.sofer = sofer;
        this.nrLocuri = nrLocuri;
    }

    @Override
    public String toString() {
        return "Autobuz{" +
                "nrInmatriculare='" + nrInmatriculare + '\'' +
                ", linie=" + linie +
                ", sofer='" + sofer + '\'' +
                ", nrLocuri=" + nrLocuri +
                '}';
    }

    public String getNrInmatriculare() {
        return nrInmatriculare;
    }

    public void setNrInmatriculare(String nrInmatriculare) {
        this.nrInmatriculare = nrInmatriculare;
    }

    public int getLinie() {
        return linie;
    }

    public void setLinie(int linie) {
        this.linie = linie;
    }

    public String getSofer() {
        return sofer;
    }

    public void setSofer(String sofer) {
        this.sofer = sofer;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }
}
