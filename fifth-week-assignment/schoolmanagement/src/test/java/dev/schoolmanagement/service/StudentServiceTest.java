package dev.schoolmanagement.service;

import dev.schoolmanagement.DTO.InstructorDTO;
import dev.schoolmanagement.DTO.StudentDTO;
import dev.schoolmanagement.entity.Gender;
import dev.schoolmanagement.entity.Student;
import dev.schoolmanagement.exceptions.StudentAgeNotValidException;
import dev.schoolmanagement.mappers.CourseMapper;
import dev.schoolmanagement.mappers.CourseMapperImpl;
import dev.schoolmanagement.mappers.StudentMapper;
import dev.schoolmanagement.mappers.StudentMapperImpl;
import dev.schoolmanagement.repository.CourseRepository;
import dev.schoolmanagement.repository.StudentRepository;
import dev.schoolmanagement.service.concrete.CourseServiceImpl;
import dev.schoolmanagement.service.concrete.StudentServiceImpl;
import dev.schoolmanagement.utility.UtilityMethods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private CourseMapper courseMapper;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseService courseService;
    private Student mockStudent;
    private StudentDTO mockStudentDTO;
    private List<Student> mockStudentList;
    private List<StudentDTO> mockStudentDTOList;


    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapperImpl();
        courseMapper = new CourseMapperImpl();
        courseService = new CourseServiceImpl(courseRepository , courseMapper);
        studentService = new StudentServiceImpl(studentRepository,studentMapper,courseService,courseRepository,courseMapper);
        mockStudent = new Student();
        mockStudentList = new ArrayList<>();
        mockStudentDTOList = new ArrayList<>();
        mockStudent.setName("Erhan");
        mockStudent.setGender(Gender.MALE);
        mockStudent.setAddress("Sarıyer");
        mockStudent.setId(1L);
        mockStudent.setBirthday(LocalDate.of(1990, 10,10));
        mockStudentList.add(mockStudent);
        mockStudentDTO = studentMapper.mapToDTO(mockStudent);
        mockStudentDTOList.add(mockStudentDTO);

    }

    @AfterEach
    void tearDown() {
        mockStudentDTOList.clear();
        mockStudentList.clear();
        mockStudent = null;
        mockStudentDTO = null;
        studentMapper = null;
        courseMapper = null;
        studentService = null;
    }

    @Test
    void Should_Return_Persisted_Objects_DTO () {
        // Given
        when(studentRepository.save(any())).thenReturn(mockStudent);

        // When
        StudentDTO actual = studentService.save(mockStudentDTO);

        // Then
        assertAll(
                () -> assertEquals(mockStudent.getId(), actual.getId()),
                () -> assertNotNull(actual)
        );

    }

    @Test
    void Should_Return_List_Of_Student_DTOs () {
        // Given
        when(studentRepository.findAll()).thenReturn(mockStudentList);

        // When
        List<StudentDTO> actual = studentService.findAll();

        // Then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(mockStudentList.size(), actual.size())
        );
    }


    @Test
    void Should_Not_Throw_Not_Found_Exception () {
        assertDoesNotThrow(() -> {
            studentRepository.deleteById(1L);
        });
    }
    @Test
    void Should_Throw_Age_Not_Valid_Exception(){
      mockStudent.setBirthday(LocalDate.of(2009,12,12));
        assertThrows(StudentAgeNotValidException.class, ()->{
            UtilityMethods.validateAge(mockStudent.getBirthday());
        });

    }

    @Test
    void Should_Return_Updated_Object () {
        // Given
        when(studentRepository.existsById(anyLong())).thenReturn(true);
        when(studentRepository.save(any())).thenReturn(mockStudent);

        // When
        StudentDTO studentDTO = studentService.update(mockStudentDTO);

        // Then
        assertEquals(mockStudent.getId(), studentDTO.getId());
    }
}