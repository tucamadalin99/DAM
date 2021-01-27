package com.example.xmlpreluare;

public class Player {
    public String name,age,position;

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

//    public Player(String name, String age, String position) {
//        this.name = name;
//        this.age = age;
//        this.position = position;
//    }
}
