package com.example.jsonpreluare;

public class Library {
    private int users;
    private String schedule;
    private String libraryName;

    @Override
    public String toString() {
        return "Library{" +
                "users=" + users +
                ", schedule='" + schedule + '\'' +
                ", libraryName='" + libraryName + '\'' +
                '}';
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public Library(int users, String schedule, String libraryName) {
        this.users = users;
        this.schedule = schedule;
        this.libraryName = libraryName;
    }
}
