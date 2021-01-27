package com.example.subiect4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "sesizari")
public class Sesizare implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    String titlu;
    String descriere;
    Tip tip;
    Date dataInregistrare;

    public Sesizare(){

    }

    @Override
    public String toString() {
        return "Sesizare{" +
                "titlu='" + titlu + '\'' +
                ", descriere='" + descriere + '\'' +
                ", tip=" + tip +
                ", dataInregistrare=" + dataInregistrare +
                '}';
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public Date getDataInregistrare() {
        return dataInregistrare;
    }

    public void setDataInregistrare(Date dataInregistrare) {
        this.dataInregistrare = dataInregistrare;
    }

    public Sesizare(String titlu, String descriere, Tip tip, Date dataInregistrare) {
        this.titlu = titlu;
        this.descriere = descriere;
        this.tip = tip;
        this.dataInregistrare = dataInregistrare;
    }


}
