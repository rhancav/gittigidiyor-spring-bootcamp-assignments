package dev.schoolmanagement.service;

import dev.schoolmanagement.entity.ClientInfo;
import dev.schoolmanagement.entity.SalaryUpdateLog;
import dev.schoolmanagement.exceptions.EntityNotFoundException;
import dev.schoolmanagement.repository.SalaryUpdateLogRepository;
import dev.schoolmanagement.service.concrete.SalaryUpdateLogImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class SalaryUpdateLogServiceTest {
    @InjectMocks
    SalaryUpdateLogImpl salaryUpdateLogService;
    @Mock
    SalaryUpdateLogRepository salaryUpdateLogRepository;
    SalaryUpdateLog salaryUpdateLog;
    List<SalaryUpdateLog> salaryUpdateLogList;

    @BeforeEach
    void setUp() {
        salaryUpdateLogService = new SalaryUpdateLogImpl(salaryUpdateLogRepository);
        salaryUpdateLogList = new ArrayList<>();
        salaryUpdateLog = new SalaryUpdateLog();
        salaryUpdateLog.setId(1);
        salaryUpdateLog.setClientInfo(ClientInfo.builder().clientIp("1.1.1.").clientURL("Local").build());
        salaryUpdateLog.setSalaryBeforeUpdate(1400.0D);
        salaryUpdateLog.setSalaryAfterUpdate(1500.0D);
        salaryUpdateLog.setCreatedDate(Instant.now());
        salaryUpdateLogList.add(salaryUpdateLog);
    }

    @AfterEach
    void tearDown() {
        salaryUpdateLogService = null;
        salaryUpdateLogList.clear();
        salaryUpdateLog = null;
    }

    @Test
    void Should_Return_List_Of_Logs(){
        // Given
        when(salaryUpdateLogRepository.findAll()).thenReturn(salaryUpdateLogList);

        // When
        List<SalaryUpdateLog> actual = salaryUpdateLogService.findAll();

        // Then
        assertEquals(salaryUpdateLogList.size(), actual.size());
    }

    @Test
    void Should_Return_Log_By_Creation_Date_And_Instructor_Id(){
        // Given
        when(salaryUpdateLogRepository.findByInstructorIdAndCreatedDate(anyLong(), any())).thenReturn(Optional.of(salaryUpdateLog));

        // When
        SalaryUpdateLog actual = salaryUpdateLogService.findByInstructorAndDate(1L, LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()));

        // Then
        assertAll(
                ()-> assertNotNull(actual),
                ()->assertEquals(salaryUpdateLog.getId(), actual.getId())
        );
    }

    @Test
    void Should_Throw_Entity_Not_Found(){
        // Given
        when(salaryUpdateLogRepository.findByInstructorIdAndCreatedDate(anyLong(), any())).thenReturn(Optional.empty());
        // Then
        assertThrows(EntityNotFoundException.class, ()->{
            salaryUpdateLogService.findByInstructorAndDate(1l, LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()));
        });
    }


}