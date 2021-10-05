package dev.schoolmanagement.service;

import dev.schoolmanagement.DTO.CourseDTO;
import dev.schoolmanagement.entity.Course;
import dev.schoolmanagement.mappers.CourseMapper;
import dev.schoolmanagement.mappers.CourseMapperImpl;
import dev.schoolmanagement.repository.CourseRepository;
import dev.schoolmanagement.service.concrete.CourseServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CourseServiceTest {
    @InjectMocks
    private CourseServiceImpl courserService;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseMapper courseMapper;
    private CourseDTO mockCourseDTO;
    private List<CourseDTO> mockCourseDTOList;
    private Course mockCourse;
    private List<Course> mockCourseList;


    @BeforeEach
    void setUp() {
        courseMapper = new CourseMapperImpl();
        courserService = new CourseServiceImpl(courseRepository, courseMapper);
        mockCourseDTO = new CourseDTO();
        mockCourseDTOList = new ArrayList<>();
        mockCourseDTO.setName("Math");
        mockCourseDTO.setCourseCode("M101");
        mockCourseDTO.setId(1L);
        mockCourseDTOList = new ArrayList<>();
        mockCourseDTOList.add(mockCourseDTO);
        mockCourse = courseMapper.mapToEntity(mockCourseDTO);
        mockCourseList = mockCourseDTOList
                .stream()
                .map((e)-> courseMapper
                        .mapToEntity(e)).collect(Collectors.toList());
    }

    @AfterEach
    void tearDown() {
        mockCourseDTO = null;
        mockCourseDTOList.clear();
        courseMapper = null;
        courserService = null;
        mockCourseList.clear();
    }

    @Test
    void Should_Throw_Already_Exists_Exception(){

    }

    @Test
    void  Should_Return_Persisted_Objects_DTO(){
        // Given
        when(courseRepository.save(any())).thenReturn(mockCourse);

        // When
        CourseDTO actual = courserService.save(mockCourseDTO);

        // Then
        assertAll(
                ()-> assertEquals(mockCourseDTO.getId(), actual.getId()),
                () -> assertNotNull(actual)
        );

    }

    @Test
    void Should_Return_List_Of_Course_DTOs(){
        // Given
        when(courseRepository.findAll()).thenReturn(mockCourseList);

        // When
        List<CourseDTO> actual = courserService.findAll();

        // Then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(mockCourseDTOList.size(), mockCourseList.size())
        );
    }
    @Test
    void Should_Return_Course_DTO_By_The_Given_Id(){
        // Given
        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(mockCourse));

        // When
        CourseDTO courseDTO = courserService.findById(mockCourseDTO.getId());

        // Then
        assertAll(
                ()-> assertEquals(mockCourse.getId(), courseDTO.getId()),
                ()-> assertNotNull(courseDTO)
        );

    }

    @Test
    void Should_Not_Throw_Not_Found_Exception(){
        assertDoesNotThrow(()->{
            courseRepository.deleteById(1L);
        });
    }

    @Test
    void Should_Return_Updated_Object(){
        // Given
        when(courseRepository.existsById(anyLong())).thenReturn(true);
        when(courseRepository.save(any())).thenReturn(mockCourse);

        // When
        CourseDTO courseDTO = courserService.update(mockCourseDTO);

        // Then
        assertEquals(mockCourse.getId(), courseDTO.getId());
    }
    @Test
    void Should_Return_Vacany_Status(){
        // Given
        when(courseRepository.vacancyExists(anyLong())).thenReturn(false);

        // When
        boolean exists = courserService.checkVacancyStatus(12L);

        // Then
        assertFalse(exists);
    }
}