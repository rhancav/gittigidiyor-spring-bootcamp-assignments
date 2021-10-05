package dev.schoolmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends AbstractEntity {
    private String name;
    private String phoneNumber;
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

    // Utility method to add courses
    public void addCourse(Course course) {
        this.courses.add(course);
    }
}
