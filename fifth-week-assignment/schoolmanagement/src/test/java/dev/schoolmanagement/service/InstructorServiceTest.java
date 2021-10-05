package dev.schoolmanagement.service;

import dev.schoolmanagement.DTO.InstructorDTO;
import dev.schoolmanagement.DTO.VisitingResearcherDTO;
import dev.schoolmanagement.DTO.request.InstructorSalaryUpdate;
import dev.schoolmanagement.entity.ClientInfo;
import dev.schoolmanagement.entity.Instructor;
import dev.schoolmanagement.entity.VisitingResearcher;
import dev.schoolmanagement.mappers.InstructorMapper;
import dev.schoolmanagement.mappers.InstructorMapperImpl;
import dev.schoolmanagement.repository.InstructorRepository;
import dev.schoolmanagement.repository.SalaryUpdateLogRepository;
import dev.schoolmanagement.service.concrete.InstructorServiceImpl;
import dev.schoolmanagement.service.concrete.SalaryUpdateLogImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cglib.core.ClassInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {
    @InjectMocks
    private InstructorServiceImpl instructorService;
    @Mock
    private InstructorRepository instructorRepository;
    @Mock
    private InstructorMapper instructorMapper;
    @Mock
    SalaryUpdateLogRepository salaryUpdateLogRepository;
    private InstructorDTO mockInstructorDTO;
    private List<InstructorDTO> mockInstructorDTOList;
    private Instructor mockInstructor;
    private List<Instructor> mockInstructorList;

    @BeforeEach
    void setUp() {
        instructorMapper = new InstructorMapperImpl();

        instructorService = new InstructorServiceImpl(instructorRepository, instructorMapper, new ClientInfo(), new SalaryUpdateLogImpl(salaryUpdateLogRepository));
        mockInstructorDTO = new VisitingResearcherDTO();
        mockInstructorDTOList = new ArrayList<>();
        mockInstructorDTO.setId(1L);
        mockInstructorDTO.setName("Erhan");
        mockInstructorDTO.setPhoneNumber("14124124");
        mockInstructorDTOList.add(mockInstructorDTO);
        mockInstructorList = new ArrayList<>();
        mockInstructor = instructorMapper.mapToVisitingResearcher((VisitingResearcherDTO) mockInstructorDTO);
        mockInstructorList.add(mockInstructor);
    }

        @AfterEach
        void tearDown () {
            mockInstructor = null;
            mockInstructorList.clear();
            instructorMapper = null;
            instructorService = null;
            mockInstructorDTOList.clear();
        }

        @Test
        void Should_Return_Persisted_Objects_DTO () {
            // Given
            when(instructorRepository.save(any())).thenReturn(mockInstructor);

            // When
            InstructorDTO actual = instructorService.save(mockInstructorDTO);

            // Then
            assertAll(
                    () -> assertEquals(mockInstructorDTO.getId(), actual.getId()),
                    () -> assertNotNull(actual)
            );

        }
        @Test
        void Should_Return_Updated_Result(){
        // Given
        VisitingResearcher visitingResearcherDTO = new VisitingResearcher();
        visitingResearcherDTO.setHourlySalary(120);
        when(instructorRepository.findById(anyLong())).thenReturn(Optional.of(visitingResearcherDTO));
        InstructorSalaryUpdate instructorSalaryUpdate = new InstructorSalaryUpdate(InstructorSalaryUpdate.UpdateType.RAISE, 20);
        // When
        VisitingResearcher instructor = (VisitingResearcher) instructorService.updateSalary(1L, instructorSalaryUpdate);
        // Then
            assertEquals(visitingResearcherDTO.getHourlySalary(), instructor.getHourlySalary());

    }

        @Test
        void Should_Return_List_Of_Instructor_DTOs () {
            // Given
            when(instructorRepository.findAll()).thenReturn(mockInstructorList);

            // When
            List<InstructorDTO> actual = instructorService.findAll();

            // Then
            assertAll(
                    () -> assertNotNull(actual),
                    () -> assertEquals(mockInstructorDTOList.size(), actual.size())
            );
        }
        @Test
        void Should_Return_Instructor_DTO_By_The_Given_Id () {
            // Given
            when(instructorRepository.findById(anyLong())).thenReturn(Optional.of(mockInstructor));

            // When
            InstructorDTO instructorDTO = instructorService.findById(mockInstructorDTO.getId());

            // Then
            assertAll(
                    () -> assertEquals(mockInstructorDTO.getId(), instructorDTO.getId()),
                    () -> assertNotNull(instructorDTO)
            );

        }

        @Test
        void Should_Not_Throw_Not_Found_Exception () {
            assertDoesNotThrow(() -> {
                instructorRepository.deleteById(1L);
            });
        }

        @Test
        void Should_Return_Updated_Object () {
            // Given
            when(instructorRepository.existsById(anyLong())).thenReturn(true);
            when(instructorRepository.save(any())).thenReturn(mockInstructor);

            // When
            InstructorDTO instructorDTO = instructorService.update(mockInstructorDTO);

            // Then
            assertEquals(mockInstructor.getId(), instructorDTO.getId());
        }


}