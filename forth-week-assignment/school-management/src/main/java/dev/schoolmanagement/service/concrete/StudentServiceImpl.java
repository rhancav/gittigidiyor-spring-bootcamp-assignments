package dev.schoolmanagement.service.concrete;

import dev.schoolmanagement.DTO.CourseDTO;
import dev.schoolmanagement.DTO.StudentDTO;
import dev.schoolmanagement.entity.Student;
import dev.schoolmanagement.exceptions.EntityNotFoundException;
import dev.schoolmanagement.exceptions.NonNullableException;
import dev.schoolmanagement.exceptions.StudentNumberForOneCourseExceededException;
import dev.schoolmanagement.mappers.CourseMapper;
import dev.schoolmanagement.mappers.StudentMapper;
import dev.schoolmanagement.repository.CourseRepository;
import dev.schoolmanagement.repository.StudentRepository;
import dev.schoolmanagement.service.CourseService;
import dev.schoolmanagement.service.StudentService;
import dev.schoolmanagement.utility.Constants;
import dev.schoolmanagement.utility.UtilityMethods;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
/**
 * StudenService implementation class. All of its methods are declared
 * readOnly at class level, writing methods are excluded by @Transactional
 * annotation.
 *
 * @author Erhan Cavdar.
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;
    StudentMapper studentMapper;
    CourseService courseService;
    CourseRepository courseRepository;
    CourseMapper courseMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public StudentDTO save(StudentDTO student) {
        if (student == null){
            throw new NonNullableException("Student cannot be null");
        }
        UtilityMethods.validateAge(student.getBirthday());
        return studentMapper.mapToDTO(studentRepository.save(studentMapper.mapToPersistable(student)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map((e) -> studentMapper.mapToDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentDTO findById(long id) {
        System.out.println("FOUND COURSE IS: " + studentRepository.findById(id).get().getCourses().stream().findFirst().get().getName());
        return studentMapper.mapToDTO(studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Constants.STUDENT_NOT_FOUND)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteById(long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException(Constants.STUDENT_NOT_FOUND);
        }
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public StudentDTO update(StudentDTO student) {
        if (student == null){
            throw new NonNullableException("Student cannot be null");
        }
        else
        if (!studentRepository.existsById(student.getId())) {
            throw new EntityNotFoundException(Constants.STUDENT_NOT_FOUND);
        }
        return studentMapper.mapToDTO(studentRepository.save(studentMapper.mapToPersistable(student)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public StudentDTO enrollCourse(Long studentId, Long courseId) {
        CourseDTO foundCourse = courseService.findById(courseId);
        if (!courseService.checkVacancyStatus(foundCourse.getId())) {
            throw new StudentNumberForOneCourseExceededException("No vacancy.");
        }
        Student foundStudent = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException(Constants.STUDENT_NOT_FOUND));
        foundCourse.addStudents(foundStudent);
        courseService.update(foundCourse);
        return studentMapper.mapToDTO(foundStudent);
    }
}