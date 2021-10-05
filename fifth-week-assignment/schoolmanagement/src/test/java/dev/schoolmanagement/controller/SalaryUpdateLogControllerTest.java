package dev.schoolmanagement.controller;

import dev.schoolmanagement.entity.ClientInfo;
import dev.schoolmanagement.entity.SalaryUpdateLog;
import dev.schoolmanagement.exceptions.EntityNotFoundException;
import dev.schoolmanagement.service.SalaryUpdateLogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SalaryUpdateLogControllerTest {
    @InjectMocks
    SalaryUpdateLogController salaryUpdateLogController;
    @Mock
    SalaryUpdateLogService salaryUpdateLogService;
    SalaryUpdateLog salaryUpdateLog;
    List<SalaryUpdateLog> salaryUpdateLogList;
    @BeforeEach
    void setUp() {
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
        salaryUpdateLogList.clear();
        salaryUpdateLog = null;
    }

    void Should_Throw_Entity_Not_Found(){
        // Given
        when(salaryUpdateLogService.findByInstructorAndDate(anyLong(),any())).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class,()->{
            salaryUpdateLogService.findByInstructorAndDate(1l,null);
        });
    }

    void Should_Return_Response_Entity_Of_Update_Log(){
        // Given
        when(salaryUpdateLogService.findAll()).thenReturn(salaryUpdateLogList);

        // When
        List<SalaryUpdateLog> actual = salaryUpdateLogController.findAll().getBody();

        // Then
        assertEquals(salaryUpdateLogList.size(), actual.size());
    }
}