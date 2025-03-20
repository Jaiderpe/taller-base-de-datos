package com.hexagonaljava.infrastructure.persistence;

public class Equipo {
    private int id;
    private String name;
    private int yearfoundation;
    private int coachId;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearfoundation() {
        return yearfoundation;
    }

    public void setYearfoundation(int yearfoundation) {
        this.yearfoundation = yearfoundation;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }
}