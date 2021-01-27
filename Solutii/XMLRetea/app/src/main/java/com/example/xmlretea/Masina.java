package com.example.xmlretea;

public class Masina {
    private String brand;
    private float motor;
    private int cp;

    public Masina(){}

    public Masina(String brand, float motor, int cp) {
        this.brand = brand;
        this.motor = motor;
        this.cp = cp;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getMotor() {
        return motor;
    }

    public void setMotor(float motor) {
        this.motor = motor;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "brand='" + brand + '\'' +
                ", motor=" + motor +
                ", cp=" + cp +
                '}';
    }
}
