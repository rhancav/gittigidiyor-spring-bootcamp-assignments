package dev.schoolmanagement.controller;

import dev.schoolmanagement.DTO.CourseDTO;
import dev.schoolmanagement.DTO.response.CreationSuccess;
import dev.schoolmanagement.DTO.response.DeletionSuccess;
import dev.schoolmanagement.DTO.response.UpdateSuccess;
import dev.schoolmanagement.exceptions.CourseAlreadyExistsException;
import dev.schoolmanagement.exceptions.EntityNotFoundException;
import dev.schoolmanagement.service.CourseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {
    @Mock
    private CourseService courseService;
    @InjectMocks
    private CourseController courseController;
    private CourseDTO mockCourse;
    private List<CourseDTO> mockCourseList;

    @BeforeEach
    void setUp() {
        mockCourse= new CourseDTO();
        mockCourse.setName("Math");
        mockCourse.setCourseCode("M101");
        mockCourse.setId(1L);
        mockCourseList = new ArrayList<>();
        mockCourseList.add(mockCourse);
    }

    @AfterEach
    void tearDown() {
        mockCourse = null;
        mockCourseList.clear();
    }
    @Test
    void Should_Return_List_Of_Courses(){
        // Given
        int mockListSize = mockCourseList.size();
        when(courseService.findAll()).thenReturn(mockCourseList);

        // When
        ResponseEntity<List<CourseDTO>> actualList = courseController.findAll();

        // Then
        assertAll(
                ()-> assertNotNull(actualList),
                () -> assertEquals(mockListSize, actualList.getBody().size())
        );
    }

    @Test
    void Should_Return_Queried_Object_With_Given_Id(){
        // Given
        when(courseService.findById(anyLong())).thenReturn(mockCourse);

        // When
        ResponseEntity<CourseDTO> actual = courseController.findById(12L);

        // Then
        assertAll(
                ()-> assertEquals(mockCourse.getId(), actual.getBody().getId()),
                () -> assertNotNull(actual.getBody())
        );
    }

    @Test
    void Should_Return_Response_Entity_Of_Persisted_Course(){
        // Given
        when(courseService.save(any())).thenReturn(mockCourse);

        // When
        ResponseEntity<CreationSuccess<CourseDTO>> responseEntity = courseController.save(mockCourse);
        CourseDTO returnedCourse = responseEntity.getBody().getSavedData();

        // Then
        assertAll(
                () -> assertEquals(mockCourse.getId(), returnedCourse.getId()),
                () -> assertNotNull(returnedCourse),
                () -> assertDoesNotThrow(() -> courseService.save(mockCourse))
        );

    }
    @Test
    void Should_Return_Update_Success_With_Updated_Object(){
        // Given
        when(courseService.update(any())).thenReturn(mockCourse);

        // When
        ResponseEntity<UpdateSuccess<CourseDTO>> responseEntity = courseController.update(mockCourse);

        // Then
        assertAll(
                ()-> assertEquals(mockCourse, responseEntity.getBody().getUpdatedData())
        );
    }
    @Test
    void Should_Return_Deletion_Success(){
        // When
        ResponseEntity<DeletionSuccess> deletionSuccess = courseController.deleteByID(mockCourse.getId());

        // Then
        assertEquals(DeletionSuccess.class, deletionSuccess.getBody().getClass());
    }

    @Test
    void Should_Throw_Course_Already_Exists_Exception()  {
        // Given
        when(courseService.save(any())).thenThrow(CourseAlreadyExistsException.class);

        // Then
       assertThrows(CourseAlreadyExistsException.class, () -> {
           courseController.save(mockCourse);
       });
    }

    @Test
    void Should_Throw_Entity_Not_Found_Exception(){
        // Given
        when(courseService.findById(anyLong())).thenThrow(EntityNotFoundException.class);
        when(courseService.update(mockCourse)).thenThrow(EntityNotFoundException.class);

        // Then
        assertAll(
                ()-> assertThrows(EntityNotFoundException.class, ()->{
                    courseController.findById(12L);
                }),
                ()-> assertThrows(EntityNotFoundException.class, ()->{
                    courseController.update(mockCourse);
                })

        );

    }




}