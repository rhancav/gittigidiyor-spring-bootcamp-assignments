package com.example.secondhomeworkerhancavdar.entity;

import javax.persistence.Entity;

@Entity
public class PermanentInstructor extends Instructor {
    private float fixedSalary;

    public PermanentInstructor() {
    }

    public PermanentInstructor(String name, String phoneNumber, float fixedSalary) {
        super(name, phoneNumber);
        this.fixedSalary = fixedSalary;
    }

    public float getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(float fixedSalary) {
        this.fixedSalary = fixedSalary;
    }

    @Override
    public String toString() {
        return "PermanentInstructor{" +
                "fixedSalary=" + fixedSalary +
                '}';
    }
}
