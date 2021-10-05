package dev.schoolmanagement.service;

import dev.schoolmanagement.DTO.CourseDTO;
import dev.schoolmanagement.DTO.StudentDTO;
import dev.schoolmanagement.entity.Student;

/**
 * Base interface for CRUD operations on Student data.
 */
public interface StudentService extends BaseService<StudentDTO> {
    /**
     * Gets the associated entities from database by using their
     * ID's, then sets the found Course entities students field
     * by using its {@link CourseDTO#addStudents(Student)} method.
     * After that persist the updated Course object back. If one of the
     * give ID's fail to match a entry at database level, throws {@link dev.schoolmanagement.exceptions.EntityNotFoundException}.
     * @param studentId of the enrolled student.
     * @param courseId  of the target course.
     * @return the updated data.
     */
    StudentDTO enrollCourse(Long studentId, Long courseId);
}