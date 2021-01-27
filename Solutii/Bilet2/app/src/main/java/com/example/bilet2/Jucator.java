package com.example.bilet2;

import android.os.Parcel;
import android.os.Parcelable;

public class Jucator implements Parcelable {
    private String nume;
    private int numar;
    private Pozitie pozitie;

    @Override
    public String toString() {
        return "Jucator{" +
                "nume='" + nume + '\'' +
                ", numar=" + numar +
                ", pozitie=" + pozitie +
                '}';
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public Pozitie getPozitie() {
        return pozitie;
    }

    public void setPozitie(Pozitie pozitie) {
        this.pozitie = pozitie;
    }

    public static Creator<Jucator> getCREATOR() {
        return CREATOR;
    }

    public Jucator(String nume, int numar, Pozitie pozitie) {
        this.nume = nume;
        this.numar = numar;
        this.pozitie = pozitie;
    }

    protected Jucator(Parcel in) {
        nume = in.readString();
        numar = in.readInt();
        pozitie = new EnumConverter().EnumfromString(in.readString());
    }

    public static final Creator<Jucator> CREATOR = new Creator<Jucator>() {
        @Override
        public Jucator createFromParcel(Parcel in) {
            return new Jucator(in);
        }

        @Override
        public Jucator[] newArray(int size) {
            return new Jucator[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeInt(numar);
        dest.writeString(new EnumConverter().StringToEnum(pozitie));
    }


}
