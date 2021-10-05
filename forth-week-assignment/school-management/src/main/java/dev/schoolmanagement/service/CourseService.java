package dev.schoolmanagement.service;


import dev.schoolmanagement.DTO.CourseDTO;
import dev.schoolmanagement.exceptions.StudentNumberForOneCourseExceededException;

/**
 * Base interface for basic CRUD operations on Course data.
 */
public interface CourseService extends BaseService<CourseDTO> {
    /**
     * Finds the course by the given ID and checks if
     * the number of students associated with the found course
     * is bigger then 20. If it is bigger, throws {@link StudentNumberForOneCourseExceededException} exception.
     *
     * @param courseId of the queried course.
     * @return true or false according to the result
     */
    boolean checkVacancyStatus(Long courseId);
}