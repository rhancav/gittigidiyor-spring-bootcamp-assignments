package dev.schoolmanagement.mappers;

import dev.schoolmanagement.DTO.CourseDTO;
import dev.schoolmanagement.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(source = "id", target = "id")
    abstract Course mapToEntity(CourseDTO source);
    @Mapping(source = "id", target = "id")
    abstract CourseDTO mapToDTO(Course source);
}
