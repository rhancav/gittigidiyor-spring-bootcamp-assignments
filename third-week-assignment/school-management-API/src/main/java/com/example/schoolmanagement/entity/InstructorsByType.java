package com.example.schoolmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * DTO for better presentation of highest nad lowest salary
 * listings, grouped by Instructor sub-types.
 */
@Data
@AllArgsConstructor
public class InstructorsByType {
    private List<VisitingResearcher> visitingResearchers;
    private List<PermanentInstructor> permanentInstructors;
}
