package com.example.schoolmanagement.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class PermanentInstructor extends Instructor {
    private float fixedSalary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PermanentInstructor that = (PermanentInstructor) o;

        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 588071453;
    }

    @Override
    public String toString() {
        return "PermanentInstructor{" +
                "fixedSalary=" + fixedSalary +
                '}';
    }
}
