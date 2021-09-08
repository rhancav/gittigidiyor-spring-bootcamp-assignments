package com.example.schoolmanagement.repository;

import com.example.schoolmanagement.entity.Instructor;
import com.example.schoolmanagement.entity.PermanentInstructor;
import com.example.schoolmanagement.entity.VisitingResearcher;
import com.example.schoolmanagement.util.StringConstants;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {
    Optional<Instructor> findInstructorByName(String name);

    void deleteByName(String name);

    // Bad
    // TODO Convert to JPQL
    @Query(nativeQuery = true, value = StringConstants.SORT_BY_SALARY_DESC_VR)
    List<VisitingResearcher> highestPayedVisitingResearchers();

    @Query(nativeQuery = true, value = StringConstants.SORT_BY_SALARY_ASC_VR)
    List<VisitingResearcher> lowestPayedVisitingResearchers();

    @Query(nativeQuery = true, value = StringConstants.SORT_BY_SALARY_DESC_PI)
    List<PermanentInstructor> highestPayedPermanentInstructors();

    @Query(nativeQuery = true, value = StringConstants.SORT_BY_SALARY_ASC_PI)
    List<PermanentInstructor> lowestPayedPermanentInstructors();

}
