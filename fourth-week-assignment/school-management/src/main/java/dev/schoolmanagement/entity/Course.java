package dev.schoolmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course extends AbstractEntity {
    private String name;
    private String courseCode;
    private float creditScore;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    private Instructor instructor;
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Set<Student> students = new HashSet<>();
}