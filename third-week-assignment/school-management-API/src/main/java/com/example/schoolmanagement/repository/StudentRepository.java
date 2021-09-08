package com.example.schoolmanagement.repository;

import com.example.schoolmanagement.entity.GenderStatistics;
import com.example.schoolmanagement.entity.Student;
import com.example.schoolmanagement.util.StringConstants;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findStudentByName(String name);

    void deleteByName(String name);

    // TODO Convert to JPQL
    @Query(nativeQuery = true, value = StringConstants.GET_GENDER_STATISTICS)
    List<GenderStatistics> countByGender();
}
