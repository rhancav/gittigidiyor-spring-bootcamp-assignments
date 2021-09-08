package com.example.schoolmanagement.service;

import com.example.schoolmanagement.entity.Instructor;
import com.example.schoolmanagement.entity.PermanentInstructor;
import com.example.schoolmanagement.entity.VisitingResearcher;

import java.util.List;

public interface InstructorService extends BaseService<Instructor> {
    List<PermanentInstructor> getTopEarningPermanentInstructors();

    List<VisitingResearcher> getTopEarningVisitingResearchers();

    List<PermanentInstructor> getLowestPayedPermanentInstructors();

    List<VisitingResearcher> getLowestPayedVisitingResearchers();


}
