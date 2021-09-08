package com.example.secondhomeworkerhancavdar.entity;


import javax.persistence.Entity;

@Entity
public class VisitingResearcher extends Instructor {
    private float hourlySalary;

    public VisitingResearcher() {
    }

    public VisitingResearcher(String name, String phoneNumber, float hourlySalary) {
        super(name, phoneNumber);
        this.hourlySalary = hourlySalary;
    }

    public float getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(float hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    @Override
    public String toString() {
        return "VisitingResearcherDTO{" +
                "hourlySalary=" + hourlySalary +
                '}';
    }
}
