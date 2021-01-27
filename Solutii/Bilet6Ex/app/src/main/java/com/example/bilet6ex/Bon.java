package com.example.bilet6ex;

import java.io.Serializable;
import java.util.Date;

public class Bon implements Serializable {
    private int numar;
    private ServiceType serviciu;
    private Date data;
    private int ghiseu;

    public Bon(int numar, ServiceType serviciu, Date data, int ghiseu) {
        this.numar = numar;
        this.serviciu = serviciu;
        this.data = data;
        this.ghiseu = ghiseu;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public ServiceType getServiciu() {
        return serviciu;
    }

    public void setServiciu(ServiceType serviciu) {
        this.serviciu = serviciu;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getGhiseu() {
        return ghiseu;
    }

    public void setGhiseu(int ghiseu) {
        this.ghiseu = ghiseu;
    }

    @Override
    public String toString() {
        return
                "Numar bon:" + numar +
                ", Serviciu:" + serviciu +
                ", Data:" + data +
                ", Ghiseu:" + ghiseu;

    }
}
