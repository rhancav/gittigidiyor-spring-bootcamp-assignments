package dev.schoolmanagement.mappers;

import dev.schoolmanagement.DTO.PermanentInstructorDTO;
import dev.schoolmanagement.DTO.VisitingResearcherDTO;
import dev.schoolmanagement.entity.PermanentInstructor;
import dev.schoolmanagement.entity.VisitingResearcher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    @Mapping(target = "fixedSalary", source = "fixedSalary")
    PermanentInstructor mapToPermanentInstructor(PermanentInstructorDTO source);

    @Mapping(target = "hourlySalary", source = "hourlySalary")
    VisitingResearcher mapToVisitingResearcher(VisitingResearcherDTO source);

    @Mapping(target = "fixedSalary", source = "fixedSalary")
    PermanentInstructorDTO mapToDTO(PermanentInstructor source);

    @Mapping(target = "hourlySalary", source = "hourlySalary")
    VisitingResearcherDTO mapToDTO(VisitingResearcher source);

}
