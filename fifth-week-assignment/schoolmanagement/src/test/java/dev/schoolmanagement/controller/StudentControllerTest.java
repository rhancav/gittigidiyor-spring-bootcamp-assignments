package dev.schoolmanagement.controller;

import dev.schoolmanagement.DTO.StudentDTO;
import dev.schoolmanagement.DTO.response.CreationSuccess;
import dev.schoolmanagement.DTO.response.DeletionSuccess;
import dev.schoolmanagement.DTO.response.UpdateSuccess;
import dev.schoolmanagement.entity.Gender;
import dev.schoolmanagement.exceptions.EntityNotFoundException;
import dev.schoolmanagement.exceptions.StudentAgeNotValidException;
import dev.schoolmanagement.exceptions.StudentAlreadyExistsException;
import dev.schoolmanagement.service.StudentService;
import dev.schoolmanagement.utility.UtilityMethods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @InjectMocks
    private StudentController studentController;
    @Mock
    private StudentService studentService;
    private StudentDTO mockStudent;
    private List<StudentDTO> mockStudentList;


    @BeforeEach
    void setUp() {
        mockStudent = new StudentDTO();
        mockStudentList = new ArrayList<>();
        mockStudent.setId(1L);
        mockStudent.setName("Erhan");
        mockStudent.setGender(Gender.MALE);
        mockStudent.setBirthday(LocalDate.now());
        mockStudent.setAddress("Sarıyer");
        mockStudentList.add(mockStudent);
    }
    @Test
    void Should_Throw_Age_Not_Valid_Exception(){
        when(studentService.save(any())).thenThrow(StudentAgeNotValidException.class);
        mockStudent.setBirthday(LocalDate.of(2009,12,12));
        assertThrows(StudentAgeNotValidException.class, ()->{
            studentController.save(mockStudent);
        });

    }

    @AfterEach
    void tearDown() {
        mockStudent = null;
        mockStudentList.clear();
    }

    @Test
    void Should_Return_List_Of_Student(){
        // Given
        int mockListSize = mockStudentList.size();
        when(studentService.findAll()).thenReturn(mockStudentList);

        // When
        ResponseEntity<List<StudentDTO>> actualList = studentController.findAll();

        // Then
        assertAll(
                ()-> assertNotNull(actualList),
                () -> assertEquals(mockListSize, actualList.getBody().size())
        );
    }

    @Test
    void Should_Return_Queried_Object_With_Given_Id(){
        // Given
        when(studentService.findById(anyLong())).thenReturn(mockStudent);

        // When
        ResponseEntity<StudentDTO> actual = studentController.findById(12L);

        // Then
        assertAll(
                ()-> assertEquals(mockStudent.getId(), actual.getBody().getId()),
                () -> assertNotNull(actual.getBody())
        );
    }

    @Test
    void Should_Return_Response_Entity_Of_Persisted_Student(){
        // Given
        when(studentService.save(any())).thenReturn(mockStudent);

        // When
        ResponseEntity<CreationSuccess<StudentDTO>> responseEntity = studentController.save(mockStudent);
        StudentDTO actual = responseEntity.getBody().getSavedData();

        // Then
        assertAll(
                () -> assertEquals(mockStudent.getId(), actual.getId()),
                () -> assertNotNull(actual),
                () -> assertDoesNotThrow(() -> studentController.save(mockStudent))
        );

    }
    @Test
    void Should_Return_Update_Success_With_Updated_Object(){
        // Given
        when(studentService.update(any())).thenReturn(mockStudent);

        // When
        ResponseEntity<UpdateSuccess<StudentDTO>> responseEntity = studentController.update(mockStudent);

        // Then
        assertAll(
                ()-> assertEquals(mockStudent, responseEntity.getBody().getUpdatedData())
        );
    }
    @Test
    void Should_Return_Deletion_Success(){
        // When
        ResponseEntity<DeletionSuccess> deletionSuccess = studentController.delete(mockStudent.getId());

        // Then
        assertEquals(DeletionSuccess.class, deletionSuccess.getBody().getClass());
    }

    @Test
    void Should_Throw_Student_Already_Exists_Exception()  {
        // Given
        when(studentService.save(any())).thenThrow(StudentAlreadyExistsException.class);

        // Then
        assertThrows(StudentAlreadyExistsException.class, () -> {
            studentController.save(mockStudent);
        });
    }

    @Test
    void Should_Throw_Entity_Not_Found_Exception(){
        // Given
        when(studentService.findById(anyLong())).thenThrow(EntityNotFoundException.class);
        when(studentService.update(mockStudent)).thenThrow(EntityNotFoundException.class);

        // Then
        assertAll(
                ()-> assertThrows(EntityNotFoundException.class, ()->{
                    studentController.findById(12L);
                }),
                ()-> assertThrows(EntityNotFoundException.class, ()->{
                    studentController.update(mockStudent);
                })

        );

    }
}