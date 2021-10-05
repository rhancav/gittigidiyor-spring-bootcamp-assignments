package dev.schoolmanagement.mappers;

import dev.schoolmanagement.DTO.CourseDTO;
import dev.schoolmanagement.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(source = "id", target = "id")
    Course mapToEntity(CourseDTO source);
    @Mapping(source = "id", target = "id")
    CourseDTO mapToDTO(Course source);
}
