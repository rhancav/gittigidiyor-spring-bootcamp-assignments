package dev.schoolmanagement.service.concrete;

import dev.schoolmanagement.DTO.CourseDTO;
import dev.schoolmanagement.exceptions.CourseAlreadyExistsException;
import dev.schoolmanagement.exceptions.EntityNotFoundException;
import dev.schoolmanagement.exceptions.NonNullableException;
import dev.schoolmanagement.mappers.CourseMapper;
import dev.schoolmanagement.repository.CourseRepository;
import dev.schoolmanagement.service.CourseService;
import dev.schoolmanagement.utility.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CourseService implementation class. All of its methods are declared
 * readOnly at class level, writing methods are excluded by @Transactional
 * annotation.
 *
 * @author Erhan Cavdar.
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {
    CourseRepository courseRepository;
    CourseMapper courseMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CourseDTO save(CourseDTO course) {
        if(course == null){
            throw new NonNullableException("Course cannot be null.");
        }
        else if (courseRepository.existsByCourseCode(course.getCourseCode()) ||courseRepository.existsById(course.getId())) {
            throw new CourseAlreadyExistsException(Constants.COURSE_ALREADY_EXISTS);
        }
        System.out.println(courseMapper.mapToEntity(course));
        return courseMapper.mapToDTO(courseRepository.save(courseMapper.mapToEntity(course)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream().map((e) -> courseMapper.mapToDTO(e)).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CourseDTO findById(long id) {
        return courseMapper.mapToDTO(courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Constants.COURSE_NOT_FOUND)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteById(long id) {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException(Constants.COURSE_NOT_FOUND);
        }
        courseRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CourseDTO update(CourseDTO course) {
        if (course == null){
            throw new NonNullableException("Course cannot be null");
        }
        else if (!courseRepository.existsById(course.getId())) {
            throw new EntityNotFoundException(Constants.COURSE_NOT_FOUND);
        }
        return courseMapper.mapToDTO(courseRepository.save(courseMapper.mapToEntity(course)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkVacancyStatus(Long courseId) {

        return courseRepository.vacancyExists(courseId);
    }
}