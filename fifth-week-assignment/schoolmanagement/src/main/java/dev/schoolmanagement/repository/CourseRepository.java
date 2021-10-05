package dev.schoolmanagement.repository;

import dev.schoolmanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCourseCode(String courseCode);
    @Query("SELECT CASE WHEN c.students.size > 20 THEN FALSE ELSE TRUE END from Course c WHERE c.id = ?1")
    boolean vacancyExists(Long courseId);
}