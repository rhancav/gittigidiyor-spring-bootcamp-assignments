package dev.schoolmanagement.controller;

import dev.schoolmanagement.DTO.ExceptionLogDTO;
import dev.schoolmanagement.service.ExceptionLogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExceptionLogControllerTest {
    @InjectMocks
    private ExceptionLogController exceptionLogController;
    @Mock
    private ExceptionLogService exceptionLogService;
    private List<ExceptionLogDTO> mockExceptionLogList;

    @BeforeEach
    void setUp() {
        mockExceptionLogList = new ArrayList<>();
        ExceptionLogDTO exceptionLogDTO = new ExceptionLogDTO();
        exceptionLogDTO.setType("EntityNotFoundException");
        exceptionLogDTO.setMessage("Entity not found.");
        exceptionLogDTO.setCreatedDate(Instant.now());
        mockExceptionLogList.add(exceptionLogDTO);
    }

    @AfterEach
    void tearDown() {
        mockExceptionLogList.clear();
    }

    @Test
    void Should_Return_List_Of_All_Exceptions(){
        // Given
        int mockListSize = mockExceptionLogList.size();
        when(exceptionLogService.findAll()).thenReturn(mockExceptionLogList);

        // When
        ResponseEntity<List<ExceptionLogDTO>> actual = exceptionLogController.findAll();

        // Then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(mockListSize, actual.getBody().size())
        );
    }
    @Test
    void Should_Return_All_By_Given_Type(){
        // Given
        when(exceptionLogService.findAllByType("EntityNotFoundException")).thenReturn(mockExceptionLogList);

        // When
        ResponseEntity<List<ExceptionLogDTO>> actual = exceptionLogController.findAllByType("EntityNotFoundException");
        ResponseEntity<List<ExceptionLogDTO>> actual2 = exceptionLogController.findAllByType("ValidationException");

        // Then
        assertAll(
                ()-> assertNotNull(actual.getBody()),
                ()-> assertEquals(mockExceptionLogList.size(), actual.getBody().size()),
                () -> assertEquals(mockExceptionLogList.get(0).getType(), actual.getBody().get(0).getType()),
                () -> assertNotEquals(actual2.getBody().size(), mockExceptionLogList.size())
        );
    }

    @Test
    void Should_Return_All_By_Given_Creation_Date(){
        // Given
        when(exceptionLogService.findAllByCreationDate(mockExceptionLogList.get(0).getCreatedDate())).thenReturn(mockExceptionLogList);

        // When
        ResponseEntity<List<ExceptionLogDTO>> actual = exceptionLogController.findAllByCreationDate(mockExceptionLogList.get(0).getCreatedDate());
        ResponseEntity<List<ExceptionLogDTO>> actual2 = exceptionLogController.findAllByCreationDate(Instant.now());

        // Then
        assertAll(
                ()-> assertNotNull(actual.getBody()),
                ()-> assertEquals(mockExceptionLogList.size(), actual.getBody().size()),
                () -> assertEquals(mockExceptionLogList.get(0).getCreatedDate(), actual.getBody().get(0).getCreatedDate()),
                () -> assertNotEquals(actual2.getBody().size(), mockExceptionLogList.size())
        );
    }

}