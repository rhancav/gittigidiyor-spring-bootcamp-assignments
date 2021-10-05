package dev.schoolmanagement.controller;

import dev.schoolmanagement.DTO.InstructorDTO;
import dev.schoolmanagement.DTO.VisitingResearcherDTO;
import dev.schoolmanagement.DTO.response.CreationSuccess;
import dev.schoolmanagement.DTO.response.DeletionSuccess;
import dev.schoolmanagement.DTO.response.UpdateSuccess;
import dev.schoolmanagement.exceptions.EntityNotFoundException;
import dev.schoolmanagement.exceptions.InstructorAlreadyExistsException;
import dev.schoolmanagement.service.InstructorService;
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
class InstructorControllerTest {
    @InjectMocks
    private InstructorController instructorController;
    @Mock
    private InstructorService instructorService;
    private VisitingResearcherDTO mockInstructor;
    private List<InstructorDTO> mockInstructorList;

    @BeforeEach
    void setUp() {
        mockInstructor = new VisitingResearcherDTO();
        mockInstructorList = new ArrayList<>();
        mockInstructor.setName("Osman");
        mockInstructor.setPhoneNumber("554902332");
        mockInstructor.setId(1L);
        mockInstructor.setHourlySalary(12.0D);
        mockInstructorList.add(mockInstructor);
    }

    @AfterEach
    void tearDown() {
        mockInstructor = null;
        mockInstructorList.clear();
    }

    @Test
    void Should_Return_List_Of_Instructors(){
        // Given
        int mockListSize = mockInstructorList.size();
        when(instructorService.findAll()).thenReturn(mockInstructorList);

        // When
        ResponseEntity<List<InstructorDTO>> actualList = instructorController.findAll();

        // Then
        assertAll(
                ()-> assertNotNull(actualList),
                () -> assertEquals(mockListSize, actualList.getBody().size())
        );
    }

    @Test
    void Should_Return_Queried_Object_With_Given_Id(){
        // Given
        when(instructorService.findById(anyLong())).thenReturn(mockInstructor);

        // When
        ResponseEntity<InstructorDTO> actual = instructorController.findById(12L);

        // Then
        assertAll(
                ()-> assertEquals(mockInstructor.getId(), actual.getBody().getId()),
                () -> assertNotNull(actual.getBody())
        );
    }

    @Test
    void Should_Return_Response_Entity_Of_Persisted_Instructor(){
        // Given
        when(instructorService.save(any())).thenReturn(mockInstructor);

        // When
        ResponseEntity<CreationSuccess<InstructorDTO>> responseEntity = instructorController.saveVisitingResearcher(mockInstructor);
        InstructorDTO actual = responseEntity.getBody().getSavedData();

        // Then
        assertAll(
                () -> assertEquals(mockInstructor.getId(), actual.getId()),
                () -> assertNotNull(actual),
                () -> assertDoesNotThrow(() -> instructorService.save(mockInstructor))
        );

    }
    @Test
    void Should_Return_Update_Success_With_Updated_Object(){
        // Given
        when(instructorService.update(any())).thenReturn(mockInstructor);

        // When
        ResponseEntity<UpdateSuccess<InstructorDTO>> responseEntity = instructorController.update(mockInstructor);

        // Then
        assertAll(
                ()-> assertEquals(mockInstructor, responseEntity.getBody().getUpdatedData())
        );
    }
    @Test
    void Should_Return_Deletion_Success(){
        // When
        ResponseEntity<DeletionSuccess> deletionSuccess = instructorController.delete(mockInstructor.getId());

        // Then
        assertEquals(DeletionSuccess.class, deletionSuccess.getBody().getClass());
    }

    @Test
    void Should_Throw_Instructor_Already_Exists_Exception()  {
        // Given
        when(instructorService.save(any())).thenThrow(InstructorAlreadyExistsException.class);

        // Then
        assertThrows(InstructorAlreadyExistsException.class, () -> {
            instructorController.saveVisitingResearcher(mockInstructor);
        });
    }

    @Test
    void Should_Throw_Entity_Not_Found_Exception(){
        // Given
        when(instructorService.findById(anyLong())).thenThrow(EntityNotFoundException.class);
        when(instructorService.update(mockInstructor)).thenThrow(EntityNotFoundException.class);

        // Then
        assertAll(
                ()-> assertThrows(EntityNotFoundException.class, ()->{
                    instructorController.findById(12L);
                }),
                ()-> assertThrows(EntityNotFoundException.class, ()->{
                    instructorController.update(mockInstructor);
                })

        );

    }

}