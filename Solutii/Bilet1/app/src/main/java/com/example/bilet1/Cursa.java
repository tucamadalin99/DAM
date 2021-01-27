package com.example.bilet1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "curse")
@TypeConverters(DateConverter.class)
public class Cursa implements Serializable, Comparable<Cursa> {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String destinatie;

            private Date data;
            private TipTaxi tipTaxi;
            @ColumnInfo(name="cost")
            private float cost;

    @Override
    public String toString() {
        return "Cursa{" +
                "destinatie='" + destinatie + '\'' +
                ", data=" + data +
                ", tipTaxi=" + tipTaxi +
                ", cost=" + cost +
                '}';
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public TipTaxi getTipTaxi() {
        return tipTaxi;
    }

    public void setTipTaxi(TipTaxi tipTaxi) {
        this.tipTaxi = tipTaxi;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Cursa(String destinatie, Date data, TipTaxi tipTaxi, float cost) {
        this.destinatie = destinatie;
        this.data = data;
        this.tipTaxi = tipTaxi;
        this.cost = cost;
    }

    @Override
    public int compareTo(Cursa o) {
        if(getData() == null || o.getData()== null)
            return  0;
        return getData().compareTo(o.getData());
    }

    public static class Converters {
        @TypeConverter
        public Date fromTimestamp(Long value) {
            return value == null ? null : new Date(value);
        }

        @TypeConverter
        public Long dateToTimestamp(Date date) {
            if (date == null) {
                return null;
            } else {
                return date.getTime();
            }
        }
    }
}
